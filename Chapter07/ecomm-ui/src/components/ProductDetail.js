import { useEffect, useState } from "react";
import { Link, useParams, useHistory } from "react-router-dom";
import ProductClient from "../api/ProductClient";
import CartClient from "../api/CartClient";
import { updateCart, useCartContext } from "../hooks/CartContext";

const ProductDetail = ({ auth }) => {
  const { id } = useParams();
  const [product, setProduct] = useState({});
  const [msg, setMsg] = new useState("");
  const history = new useHistory();
  const { cartItems, dispatch } = useCartContext();
  const cartClient = new CartClient(auth);

  const callAddItemApi = async () => {
    const qty = findQty(product.id);
    return cartClient.addOrUpdate({
      id: product.id,
      quantity: qty + 1,
      unitPrice: product.price,
    });
  };

  const findQty = (id) => {
    const idx = cartItems.findIndex((i) => i.id === id);
    if (~idx) {
      return cartItems[idx].quantity;
    }
    return 0;
  };

  const checkLogin = () => {
    if (!auth.token) {
      history.push("/login");
      return false;
    }
    return true;
  };

  const add = async () => {
    const isLoggedIn = checkLogin();
    if (isLoggedIn && product?.id) {
      const res = await callAddItemApi();
      if (res && res.success) {
        console.log(res.data);
        if (res.data?.length > 0) {
          setMsg("Product added to bag.");
          dispatch(updateCart(res.data));
        }
      } else {
        setMsg(res && typeof res === "string" ? res : res.error.message);
      }
    }
  };

  const buy = async () => {
    const isLoggedIn = checkLogin();
    if (isLoggedIn && product?.id) {
      const res = await callAddItemApi();
      if (res && res.success) {
        history.push("/cart");
      } else {
        setMsg(res && typeof res === "string" ? res : res.error.message);
      }
    }
  };

  useEffect(() => {
    async function getProduct(id) {
      const client = new ProductClient();
      const res = await client.fetch(id);
      if (res && res.success) {
        setProduct(res.data);
      }
    }
    async function fetchCart(auth) {
      const res = await new CartClient(auth).fetch();
      if (res && res.success) {
        console.log(res.data);
        dispatch(updateCart(res.data.items));
      }
    }
    if (auth?.token) fetchCart(auth);
    getProduct(id);
  }, [id]);

  return (
    <div
      id={product?.id}
      className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 my-1 w-full md:w-1/2 lg:my-4"
    >
      <figure className="bg-gray-100 rounded-xl p-8 md:p-0 xs:flex md:block">
        <img
          className="w-72 h-72 mx-auto"
          src={product?.imageUrl}
          alt={product?.name}
        />
        <div className="pt-4 md:p-6 text-center xs:pl-2 md:text-left space-y-4">
          <form className="flex-auto">
            <div className="flex flex-wrap items-center justify-between">
              <h1 className="w-full flex-none font-bold mb-2.5 text-left">
                <Link to="/">{product?.name}</Link>
              </h1>
              <div className="text-4xl leading-7 font-bold text-purple-600">
                {"$"}
                {product?.price?.toFixed(2)}
              </div>
              <div className="text-sm font-medium text-gray-400 ml-3">
                In stock
              </div>
            </div>
            <div className="font-semibold pt-8">{product?.description}</div>

            {product && product.tag ? (
              <div className="font-semibold py-8 flex flex-row flex-wrap">
                {" "}
                {product?.tag?.map((t) => (
                  <div>
                    <span className="bg-yellow-400 p-2 rounded-2xl">
                      {t.name}{" "}
                    </span>
                    &nbsp;
                  </div>
                ))}{" "}
              </div>
            ) : (
              ""
            )}
            <div className="flex space-x-3 mt-8 mb-4 text-sm font-semibold">
              <div className="flex-auto flex justify-between">
                <button
                  className="w-1/2 flex items-center justify-center rounded-full bg-purple-700 text-white py-2 md:w-1/4"
                  type="button"
                  onClick={buy}
                >
                  Buy now
                </button>
                <button
                  className="flex items-center justify-center rounded-full bg-purple-50 text-purple-700"
                  type="button"
                  onClick={add}
                >
                  Add to bag
                </button>
              </div>
            </div>
            <p className="text-sm text-gray-500 text-left">
              Free shipping on all local orders.
            </p>
          </form>
        </div>
      </figure>
    </div>
  );
};

export default ProductDetail;
