import ProductCard from "./ProductCard";

const Products = ({ auth, productList }) => {
  return (
    <>
      {productList.map((item) => (
        <ProductCard key={item.id} product={item} auth={auth} />
      ))}
    </>
  );
};

export default Products;
