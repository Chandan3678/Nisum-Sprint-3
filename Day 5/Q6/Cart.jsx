import React, { useReducer } from 'react';
import './Cart.css';

const initialState = {
  quantity: 1,
  pricePerItem: 499, // Example price
  isLoading: false,
};

const reducer = (state, action) => {
  switch (action.type) {
    case 'INCREMENT_START':
    case 'DECREMENT_START':
      return { ...state, isLoading: true };

    case 'INCREMENT':
      return {
        ...state,
        quantity: state.quantity + 1,
        isLoading: false,
      };

    case 'DECREMENT':
      return {
        ...state,
        quantity: state.quantity > 1 ? state.quantity - 1 : 1,
        isLoading: false,
      };

    default:
      return state;
  }
};

const Cart = () => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const handleIncrement = () => {
    dispatch({ type: 'INCREMENT_START' });
    setTimeout(() => {
      dispatch({ type: 'INCREMENT' });
    }, 300); // Simulate delay
  };

  const handleDecrement = () => {
    dispatch({ type: 'DECREMENT_START' });
    setTimeout(() => {
      dispatch({ type: 'DECREMENT' });
    }, 300); // Simulate delay
  };

  const totalPrice = state.quantity * state.pricePerItem;

  return (
    <div className="cart-container">
      <h2>Cart Item</h2>
      <p>Price per item: ${state.pricePerItem}</p>

      <div className="quantity-controls">
        <button 
          onClick={handleDecrement} 
          disabled={state.quantity === 1 || state.isLoading}
        >
          -
        </button>
        <span>{state.quantity}</span>
        <button 
          onClick={handleIncrement} 
          disabled={state.isLoading}
        >
          +
        </button>
      </div>

      <p>Total Price: ${totalPrice}</p>
    </div>
  );
};

export default Cart;
