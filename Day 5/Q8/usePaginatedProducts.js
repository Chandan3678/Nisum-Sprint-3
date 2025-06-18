import { useState, useEffect, useRef, useCallback } from 'react';

export default function usePaginatedProducts(category) {
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(1);
  const [hasMore, setHasMore] = useState(true);
  const [loading, setLoading] = useState(false);

  const observer = useRef();

  const loadMore = useCallback((node) => {
    if (loading || !hasMore) return;
    if (observer.current) observer.current.disconnect();

    observer.current = new IntersectionObserver((entries) => {
      if (entries[0].isIntersecting) {
        setPage((prev) => prev + 1);
      }
    });

    if (node) observer.current.observe(node);
  }, [loading, hasMore]);

  useEffect(() => {
    setProducts([]);
    setPage(1);
    setHasMore(true);
  }, [category]);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      // Simulated API fetch
      const res = await fetch(
        `https://fakestoreapi.com/products/category/${category}?limit=5&page=${page}`
      );
      const data = await res.json();

      if (Array.isArray(data) && data.length > 0) {
        setProducts((prev) => [...prev, ...data]);
      } else {
        setHasMore(false);
      }

      setLoading(false);
    };

    fetchData();
  }, [page, category]);

  return { products, loadMore, loading };
}
