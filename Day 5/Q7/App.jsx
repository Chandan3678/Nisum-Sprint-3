import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import ProductCard from './ProductCard';
import useWishlist from './useWishlist';

const sampleProducts = [
  { id: 1, title: 'iPhone 15', price: 1099, description: 'Latest Apple model' },
  { id: 2, title: 'Galaxy S24', price: 999, description: 'Latest Samsung phone' },
  { id: 3, title: 'Pixel 9', price: 899, description: 'Google AI-powered phone' },
];

const Home = () => (
  <div>
    <h1>All Products</h1>
    <div style={{ display: 'flex', flexWrap: 'wrap' }}>
      {sampleProducts.map((p) => (
        <ProductCard key={p.id} product={p} />
      ))}
    </div>
  </div>
);

const WishlistPage = () => {
  const { wishlist } = useWishlist();
  return (
    <div>
      <h1>My Wishlist ❤️</h1>
      {wishlist.length === 0 ? (
        <p>No items in wishlist.</p>
      ) : (
        <div style={{ display: 'flex', flexWrap: 'wrap' }}>
          {wishlist.map((p) => (
            <ProductCard key={p.id} product={p} />
          ))}
        </div>
      )}
    </div>
  );
};

function App() {
  return (
    <Router>
      <nav style={{ margin: '20px' }}>
        <Link to="/" style={{ marginRight: '15px' }}>Home</Link>
        <Link to="/wishlist">Wishlist</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/wishlist" element={<WishlistPage />} />
      </Routes>
    </Router>
  );
}

export default App;
