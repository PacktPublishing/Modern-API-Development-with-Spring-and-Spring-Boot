import React, { createContext, useReducer, useContext } from "react";

// Context
export const CartContext = createContext();

// Custom hook to consume cart context
function useCartContext() {
  return useContext(CartContext);
}
// ----------------------------------------------------------------

// Actions
export const UPDATE_CART = "UPDATE_CART";
export const ADD_ITEM = "ADD_ITEM";
export const REMOVE_ITEM = "REMOVE_ITEM";

export function updateCart(items) {
  return { type: UPDATE_CART, items };
}
export function addItem(item) {
  return { type: ADD_ITEM, item };
}
export function removeItem(index) {
  return { type: REMOVE_ITEM, index };
}
// ----------------------------------------------------------------

// Reducer - update the state based on given action
export function cartReducer(state, action) {
  switch (action.type) {
    case UPDATE_CART:
      return [...action?.items];
    case ADD_ITEM:
      return [...state, action.item];
    case REMOVE_ITEM:
      const list = [...state];
      list.splice(action.index, 1);
      return list;
    default:
      return state;
  }
}

// ----------------------------------------------------------------

const CartContextProvider = (props) => {
  const [cartItems, dispatch] = useReducer(cartReducer, []);
  const cartData = { cartItems, dispatch };
  return <CartContext.Provider value={cartData} {...props} />;
};

export { CartContextProvider, useCartContext };
