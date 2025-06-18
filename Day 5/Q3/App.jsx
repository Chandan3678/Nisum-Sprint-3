import React from 'react';
import ProductCard from './ProductCard';

function App() {
  return (
    <div>
      {/* With props */}
      <ProductCard 
        title="iPhone 15" 
        price={1099} 
        description="Latest model with improved battery life." 
      />

      {/* Without props: will show default values */}
      <ProductCard />
    </div>
  );
}

export default App;
