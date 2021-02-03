import { useEffect, useState } from "react";
import { Link, useHistory } from "react-router-dom";
import CartClient from "../api/CartClient";
import CustomerClient from "../api/CustomerClient";
import OrderClient from "../api/OrderClient";
import { removeItem, updateCart, useCartContext } from "../hooks/CartContext";
import CartItem from "./CartItem";

const Cart = ({ auth }) => {
  const [grandTotal, setGrandTotal] = useState(0);
  const [noRecMsg, setNoRecMsg] = useState("Loading...");
  const history = useHistory();
  const cartClient = new CartClient(auth);
  const orderClient = new OrderClient(auth);
  const customerClient = new CustomerClient(auth);
  const { cartItems, dispatch } = useCartContext();

  const calTotal = (items) => {
    let total = 0;
    items?.forEach((i) => (total = total + i?.unitPrice * i?.quantity));
    return total.toFixed(2);
  };

  const increaseQty = async (id) => {
    const idx = cartItems.findIndex((i) => i.id === id);
    if (~idx) {
      cartItems[idx].quantity = cartItems[idx].quantity + 1;
      const res = await cartClient.addOrUpdate(cartItems[idx]);
      if (res && res.success) {
        console.log(res.data);
        refreshCart(res.data);
        if (res.data?.length < 1) {
          setNoRecMsg("Cart is empty.");
        }
      } else {
        setNoRecMsg(res && typeof res === "string" ? res : res.error.message);
      }
    }
  };

  const decreaseQty = async (id) => {
    const idx = cartItems.findIndex((i) => i.id === id);
    if (~idx && cartItems[idx].quantity <= 1) {
      return deleteItem(id);
    } else if (cartItems[idx]?.quantity > 1) {
      cartItems[idx].quantity = cartItems[idx].quantity - 1;
      const res = await cartClient.addOrUpdate(cartItems[idx]);
      if (res && res.success) {
        refreshCart(res.data);
        if (res.data?.length < 1) {
          setNoRecMsg("Cart is empty.");
        }
        return;
      } else {
        setNoRecMsg(res && typeof res === "string" ? res : res?.error?.message);
      }
    }
  };

  const deleteItem = async (id) => {
    const idx = cartItems.findIndex((i) => i.id === id);
    if (~idx) {
      const res = await cartClient.remove(cartItems[idx].id);
      if (res && res.success) {
        dispatch(removeItem(idx));
        if (res.data?.length < 1) {
          setNoRecMsg("Item is removed.");
        }
      } else {
        setNoRecMsg(
          res && typeof res === "string"
            ? res
            : "There is an error performing the remove."
        );
      }
    }
  };

  const refreshCart = (items) => {
    setGrandTotal(calTotal(items));
    dispatch(updateCart(items));
  };

  useEffect(() => {
    async function fetch() {
      const res = await cartClient.fetch();
      if (res && res.success) {
        console.log(res.data);
        refreshCart(res.data.items);
        if (res.data?.items && res.data.items?.length < 1) {
          setNoRecMsg("Cart is empty.");
        }
      } else {
        setNoRecMsg(res && typeof res === "string" ? res : res.error.message);
      }
    }
    fetch();
  }, []);

  const checkout = async () => {
    const res = await customerClient.fetch();
    if (res && res.success) {
      const payload = {
        address: { id: res.data.addressId },
        card: { id: res.data.cardId },
      };
      const orderRes = await orderClient.add(payload);
      if (orderRes && orderRes.success) {
        history.push("/orders");
      } else {
        setNoRecMsg(
          orderRes && typeof orderRes === "string"
            ? orderRes
            : "Couldn't process checkout."
        );
      }
    } else {
      setNoRecMsg(
        res && typeof res === "string" ? res : "error retreiving customer"
      );
    }
  };

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div className="flex shadow-md my-4">
        <div className="w-3/4 bg-gray-100 px-10 py-5">
          <div className="flex justify-between border-b pb-8">
            <h1 className="font-semibold text-2xl">Shopping Cart</h1>
            <h2 className="font-semibold text-2xl">
              {cartItems?.length} Items
            </h2>
          </div>
          <div className="flex mt-10 mb-5">
            <h3 className="font-semibold text-gray-600 text-xs uppercase w-2/5">
              Product Details
            </h3>
            <h3 className="font-semibold text-center text-gray-600 text-xs uppercase w-1/5">
              Quantity
            </h3>
            <h3 className="font-semibold text-center text-gray-600 text-xs uppercase w-1/5">
              Price
            </h3>
            <h3 className="font-semibold text-center text-gray-600 text-xs uppercase w-1/5">
              Total
            </h3>
          </div>
          {cartItems && cartItems.length > 0 ? (
            cartItems?.map((i) => (
              <CartItem
                item={i}
                key={i.id}
                increaseQty={increaseQty}
                decreaseQty={decreaseQty}
                removeItem={deleteItem}
              />
            ))
          ) : (
            <div className="flex items-center hover:bg-gray-200 -mx-6 px-6 py-5">
              {noRecMsg}
            </div>
          )}
          <Link
            to="/"
            className="flex font-semibold text-indigo-600 text-sm mt-10"
          >
            <svg
              className="fill-current mr-2 text-indigo-600 w-4"
              viewBox="0 0 448 512"
            >
              <path d="M134.059 296H436c6.627 0 12-5.373 12-12v-56c0-6.627-5.373-12-12-12H134.059v-46.059c0-21.382-25.851-32.09-40.971-16.971L7.029 239.029c-9.373 9.373-9.373 24.569 0 33.941l86.059 86.059c15.119 15.119 40.971 4.411 40.971-16.971V296z" />
            </svg>
            Continue Shopping
          </Link>
        </div>

        <div id="summary" className="bg-gray-50 w-1/4 px-8 py-10">
          <h1 className="font-semibold text-2xl border-b pb-8">
            Order Summary
          </h1>
          <div className="flex justify-between mt-10 mb-5">
            <span className="font-semibold text-sm uppercase">
              Items {cartItems?.length}
            </span>
            <span className="font-semibold text-sm">${grandTotal}</span>
          </div>
          <div>
            <label className="font-medium inline-block mb-3 text-sm uppercase">
              Shipping
            </label>
            <select className="block p-2 text-gray-600 w-full text-sm">
              <option>Free shipping - $0.00</option>
            </select>
          </div>
          <div className="py-10">
            <label
              htmlFor="promo"
              className="font-semibold inline-block mb-3 text-sm uppercase"
            >
              Promo Code
            </label>
            <input
              type="text"
              id="promo"
              placeholder="Enter your code"
              className="p-2 text-sm w-full cursor-not-allowed"
              disabled
            />
          </div>
          <button
            className="bg-red-500 hover:bg-red-600 px-5 py-2 text-sm text-white uppercase cursor-not-allowed disabled:opacity-50"
            disabled
          >
            Apply
          </button>
          <div className="border-t mt-8">
            <div className="flex font-semibold justify-between py-6 text-sm uppercase">
              <span>Total cost</span>
              <span>${grandTotal}</span>
            </div>
            <button
              className="bg-indigo-500 font-semibold hover:bg-indigo-600 py-3 text-sm text-white uppercase w-full disabled:opacity-50"
              onClick={checkout}
              disabled={grandTotal == 0 ? true : false}
            >
              Checkout
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cart;
