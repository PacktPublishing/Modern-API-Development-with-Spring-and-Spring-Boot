import { useEffect, useState } from "react";
import CartClient from "../api/CartClient";
import ProductClient from "../api/ProductClient";
import { updateCart, useCartContext } from "../hooks/CartContext";
import Products from "./Products";

const ProductList = ({ auth }) => {
  const [productList, setProductList] = useState();
  const [noRecMsg, setNoRecMsg] = useState("Loading...");
  const { dispatch } = useCartContext();

  useEffect(() => {
    async function fetchProducts() {
      const res = await new ProductClient().fetchList();
      if (res && res.success) {
        setProductList(res.data);
      } else {
        setNoRecMsg(res);
      }
    }
    async function fetchCart(auth) {
      const res = await new CartClient(auth).fetch();
      if (res && res.success) {
        console.log(res.data);
        dispatch(updateCart(res.data.items));
        if (res.data?.items && res.data.items?.length < 1) {
          setNoRecMsg("Cart is empty.");
        }
      } else {
        setNoRecMsg(res && typeof res === "string" ? res : res?.error?.message);
      }
    }
    if (auth?.token) fetchCart(auth);
    fetchProducts();
  }, []);

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      {productList ? (
        <div className="flex flex-wrap -mx-1 lg:-mx-4">
          <Products auth={auth} productList={productList ? productList : []} />
        </div>
      ) : (
        <div className="text-lg font-semibold">{noRecMsg}</div>
      )}
    </div>
  );
};
export default ProductList;
