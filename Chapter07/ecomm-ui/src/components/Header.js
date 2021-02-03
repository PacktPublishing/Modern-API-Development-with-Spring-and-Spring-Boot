import { useState, useEffect } from "react";
import { Link, useHistory } from "react-router-dom";
import Button from "./Button";

const Header = ({ userInfo, auth }) => {
  const history = useHistory();
  const [open, setOpen] = useState(false);
  const [loggedIn, setLoggedIn] = useState(false);

  const handleClick = (event) => {
    const target = document.querySelector("#headerUserMenu");
    const withinBoundaries = event.composedPath().includes(target);
    if (!withinBoundaries) {
      if (open) setOpen(false);
    }
  };

  const handleLogout = async (e) => {
    await auth.logoutUser({ refreshToken: userInfo.refreshToken });
    if (open) setOpen(false);
    history.push("/");
  };

  useEffect(() => {
    setLoggedIn(userInfo ? true : false);
  }, [userInfo]);

  useEffect(() => {
    window.addEventListener("click", handleClick);
    // cleanup this component
    return () => {
      window.removeEventListener("click", handleClick);
    };
  });

  return (
    <div>
      <nav className="bg-gray-800">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex items-center justify-between h-16">
            <div className="flex items-center">
              <div className="flex-shrink-0 text-gray-300 font-bold">
                <Link to="/">Ecommerce App</Link>
              </div>
              <div className="hidden md:block">
                <div className="ml-10 flex items-baseline space-x-4">
                  {/* Current: "bg-gray-900 text-white", Default: "text-gray-300 hover:bg-gray-700 hover:text-white" */}
                  <Link
                    to="/cart"
                    className="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                  >
                    Cart
                  </Link>
                </div>
              </div>
            </div>
            <div className="hidden md:block">
              <div className="ml-4 flex items-center md:ml-6">
                <div className="ml-3 relative">
                  <div>
                    <Button
                      id="user-menu"
                      hasPopup="true"
                      onClick={
                        loggedIn
                          ? () => setOpen(!open)
                          : () => history.push("/Login")
                      }
                      color={loggedIn ? "green" : "red"}
                      // text={loggedIn ? userInfo?.username + " ▼" : "Login"}
                      text={
                        loggedIn ? (
                          <div className="flex items-center justify-center">
                            <div className="flex-shrink-0 pr-1">
                              <div className="bg-gray-800 rounded-full px-2 group-hover:bg-green-700 group-hover:text-gray-200">
                                <span className="font-bold align-text-bottom">
                                  {userInfo?.username.toUpperCase().charAt(0)}
                                </span>
                              </div>
                            </div>
                            <span className="capitalize">
                              {userInfo?.username} ▼
                            </span>
                          </div>
                        ) : (
                          "Login"
                        )
                      }
                    >
                      <span className="sr-only">Open user menu</span>
                    </Button>
                  </div>
                  <div
                    id="headerUserMenu"
                    className="origin-top-right absolute right-0 mt-2 w-48 rounded-md shadow-lg py-1 bg-white ring-1 ring-black ring-opacity-5"
                    role="menu"
                    aria-orientation="vertical"
                    aria-labelledby="user-menu"
                    style={{ display: open && loggedIn ? "block" : "none" }}
                  >
                    <button
                      type="text"
                      onClick={() => {
                        history.push("/cart");
                        setOpen(!open);
                      }}
                      className="w-full text-left block px-4 py-2 text-sm text-gray-700 hover:bg-indigo-100"
                      role="menuitem"
                    >
                      Cart
                    </button>
                    <button
                      type="text"
                      onClick={() => {
                        history.push("/orders");
                        setOpen(!open);
                      }}
                      className="w-full text-left block px-4 py-2 text-sm text-gray-700 hover:bg-indigo-100"
                      role="menuitem"
                    >
                      Orders
                    </button>
                    <button
                      type="text"
                      onClick={() => {
                        history.push("/");
                        setOpen(!open);
                      }}
                      className="w-full text-left block px-4 py-2 text-sm text-gray-700 hover:bg-indigo-100"
                      role="menuitem"
                    >
                      Product List
                    </button>
                    <button
                      type="text"
                      onClick={handleLogout}
                      className="w-full text-left block px-4 py-2 text-sm text-gray-700 hover:bg-indigo-100"
                      role="menuitem"
                    >
                      Sign out
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <div className="-mr-2 flex md:hidden">
              {/* Mobile menu button */}
              <button
                className="bg-gray-800 inline-flex items-center justify-center p-2 rounded-md text-gray-400 hover:text-white hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-800 focus:ring-white"
                onClick={() => setOpen(!open)}
              >
                <span className="sr-only">Open main menu</span>
                <svg
                  className="block h-6 w-6"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                  aria-hidden="true"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="2"
                    d="M4 6h16M4 12h16M4 18h16"
                  />
                </svg>
                <svg
                  className="hidden h-6 w-6"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                  aria-hidden="true"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="2"
                    d="M6 18L18 6M6 6l12 12"
                  />
                </svg>
              </button>
            </div>
          </div>
        </div>

        <div className={`${open ? "block md:hidden" : "hidden md:hidden"}`}>
          <div className="px-2 pt-2 pb-3 space-y-1 sm:px-3">
            <button
              type="text"
              onClick={() => history.push("/cart")}
              style={{ display: open && loggedIn ? "block" : "none" }}
              className="text-gray-300 hover:bg-gray-700 hover:text-white block px-3 py-2 rounded-md text-base font-medium"
            >
              Cart
            </button>
            <button
              type="text"
              onClick={() => history.push("/login")}
              style={{ display: !(open && loggedIn) ? "block" : "none" }}
              className="text-gray-300 hover:bg-gray-700 hover:text-white block px-3 py-2 rounded-md text-base font-medium"
            >
              Login
            </button>
          </div>
          <div
            className="pt-4 pb-3 border-t border-gray-700"
            style={{ display: open && loggedIn ? "block" : "none" }}
          >
            <div className="flex items-center px-5">
              <div className="flex-shrink-0">
                <div className="bg-gray-100 rounded-full px-3 py-1">
                  <span className="font-bold">
                    {loggedIn
                      ? userInfo?.username.toUpperCase().charAt(0)
                      : "Login"}
                  </span>
                </div>
              </div>
              <div className="ml-3">
                <div className="text-base font-medium leading-none text-white">
                  {loggedIn ? userInfo?.username : "Login"}
                </div>
              </div>
            </div>
            <div className="mt-3 px-2 space-y-1">
              <button
                type="text"
                onClick={() => history.push("/cart")}
                style={{ display: open && loggedIn ? "block" : "none" }}
                className="w-full text-left block px-3 py-2 rounded-md text-base font-medium text-gray-400 hover:text-white hover:bg-gray-700"
              >
                Cart
              </button>
              <button
                type="text"
                onClick={() => history.push("/orders")}
                style={{ display: open && loggedIn ? "block" : "none" }}
                className="w-full text-left block px-3 py-2 rounded-md text-base font-medium text-gray-400 hover:text-white hover:bg-gray-700"
              >
                Orders
              </button>
              <button
                type="text"
                onClick={() => history.push("/")}
                style={{ display: open && loggedIn ? "block" : "none" }}
                className="w-full text-left block px-3 py-2 rounded-md text-base font-medium text-gray-400 hover:text-white hover:bg-gray-700"
              >
                Product List
              </button>
              <button
                type="text"
                onClick={handleLogout}
                style={{ display: open && loggedIn ? "block" : "none" }}
                className="w-full text-left block px-3 py-2 rounded-md text-base font-medium text-gray-400 hover:text-white hover:bg-gray-700"
              >
                Sign out
              </button>
            </div>
          </div>
        </div>
      </nav>
    </div>
  );
};

export default Header;
