import React from 'react';
import './ProductCard.css'; // External CSS import

const ProductCard = ({ title, price, description }) => {
  const inlineStyles = {
    border: '1px solid black',
    padding: '20px',
    margin: '20px auto',
    maxWidth: '300px',
    boxShadow: '0 4px 8px rgba(0,0,0,0.1)',
    backgroundColor: '#f9f9f9',
    borderRadius: '10px'
  };

  return (
    <div style={inlineStyles} className="product-card">
      <h2 className="product-title">{title}</h2>
      <p className="product-price">Price: ${price}</p>
      <p className="product-description">{description}</p>
    </div>
  );
};

ProductCard.defaultProps = {
  title: 'Unknown Product',
  price: 0,
  description: 'No description available.',
};

export default ProductCard;
