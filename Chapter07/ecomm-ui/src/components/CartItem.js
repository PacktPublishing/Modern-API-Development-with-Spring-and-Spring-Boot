import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const CartItem = ({ item, increaseQty, decreaseQty, removeItem }) => {
  //const [item, setItem] = useState(cartItem);
  const des = item ? item.description?.split(".") : [];
  const author = des && des.length > 0 ? des[des.length - 1] : "";
  const [total, setTotal] = useState();

  const calTotal = (item) => {
    setTotal((item?.unitPrice * item?.quantity)?.toFixed(2));
  };

  const updateQty = (qty) => {
    if (qty === -1) {
      decreaseQty(item?.id);
    } else if (qty === 1) {
      increaseQty(item?.id);
    } else {
      return false;
    }
    calTotal(item);
  };

  useEffect(() => {
    calTotal(item);
  }, []);

  return (
    <div className="flex items-center hover:bg-gray-200 -mx-8 px-6 py-5">
      <div className="flex w-2/5">
        <div className="w-32">
          <img className="h-24" src={item?.imageUrl} alt="" />
        </div>
        <div className="flex flex-col justify-between ml-4 flex-grow">
          <Link
            to={"/products/" + item.id}
            className="font-bold text-sm text-indigo-500 hover:text-indigo-700"
          >
            {item?.name}
          </Link>
          <span className="text-xs">Author: {author}</span>
          <button
            className="font-semibold hover:text-red-500 text-indigo-500 text-xs text-left"
            onClick={() => removeItem(item.id)}
          >
            Remove
          </button>
        </div>
      </div>
      <div className="flex items-center justify-center w-1/5">
        <span className="cursor-pointer" onClick={() => updateQty(-1)}>
          <svg className="fill-current text-gray-600 w-3" viewBox="0 0 448 512">
            <path d="M416 208H32c-17.67 0-32 14.33-32 32v32c0 17.67 14.33 32 32 32h384c17.67 0 32-14.33 32-32v-32c0-17.67-14.33-32-32-32z" />
          </svg>
        </span>

        <input
          className="mx-2 border text-center w-8"
          type="text"
          readOnly
          value={item?.quantity}
        />
        <span className="cursor-pointer" onClick={() => updateQty(1)}>
          <svg className="fill-current text-gray-600 w-3" viewBox="0 0 448 512">
            <path d="M416 208H272V64c0-17.67-14.33-32-32-32h-32c-17.67 0-32 14.33-32 32v144H32c-17.67 0-32 14.33-32 32v32c0 17.67 14.33 32 32 32h144v144c0 17.67 14.33 32 32 32h32c17.67 0 32-14.33 32-32V304h144c17.67 0 32-14.33 32-32v-32c0-17.67-14.33-32-32-32z" />
          </svg>
        </span>
      </div>
      <span className="text-center w-1/5 font-semibold text-sm">
        {item?.unitPrice?.toFixed(2)}
      </span>
      <span className="text-center w-1/5 font-semibold text-sm">${total}</span>
    </div>
  );
};

export default CartItem;
