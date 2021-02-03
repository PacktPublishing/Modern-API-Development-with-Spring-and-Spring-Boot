import { useLocation } from "react-router-dom";

const NotFound = () => {
  const location = useLocation();
  return (
    <div class="flex flex-col items-center justify-center">
      <div class="text-xl font-bold text-indigo-600">404 - Not found</div>
      <p class="italic">
        No match for "<code>{location.pathname}</code>"
      </p>
    </div>
  );
};

export default NotFound;
