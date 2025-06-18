import React, { useState, useEffect } from 'react';
import './Counter.css';

const Counter = () => {
  const [count, setCount] = useState(0);

  // Log count changes whenever count updates
  useEffect(() => {
    console.log(`Count changed to: ${count}`);
  }, [count]);

  const increment = () => setCount(prev => prev + 1);
  const decrement = () => setCount(prev => (prev > 0 ? prev - 1 : 0));
  const reset = () => setCount(0);

  return (
    <div className="counter-container">
      <h1>Count: {count}</h1>
      <button className="btn increment" onClick={increment}>Increment</button>
      <button 
        className="btn decrement" 
        onClick={decrement} 
        disabled={count === 0}
      >
        Decrement
      </button>
      <button className="btn reset" onClick={reset}>Reset</button>
    </div>
  );
};

export default Counter;
