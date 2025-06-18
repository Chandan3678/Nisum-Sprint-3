import React from 'react';
import useWishlist from './useWishlist';
import './ProductCard.css';

const ProductCard = ({ product }) => {
  const { toggleWishlist, isWishlisted } = useWishlist();

  return (
    <div className="product-card">
      <h2>
        {product.title}
        <span
          className="wishlist-icon"
          onClick={() => toggleWishlist(product)}
        >
          {isWishlisted(product.id) ? '❤️' : '🤍'}
        </span>
      </h2>
      <p>Price: ${product.price}</p>
      <p>{product.description}</p>
    </div>
  );
};

export default ProductCard;
