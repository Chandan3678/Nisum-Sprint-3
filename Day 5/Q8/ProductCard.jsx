import React from 'react';
import './ProductCard.css';

const ProductCard = ({ product }) => (
  <div className="product-card">
    <h3>{product.title}</h3>
    <p>${product.price}</p>
    <p>{product.description.slice(0, 50)}...</p>
  </div>
);

export default ProductCard;
