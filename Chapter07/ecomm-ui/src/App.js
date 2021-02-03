import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import ProductList from "./components/ProductList";
import Login from "./components/Login";
import useToken from "./hooks/useToken";
import Cart from "./components/Cart";
import ProductDetail from "./components/ProductDetail";
import NotFound from "./components/NotFound";
import Auth from "./api/Auth";
import { CartContextProvider } from "./hooks/CartContext";
import Orders from "./components/Orders";

function App() {
  const { token, setToken } = useToken();
  const auth = new Auth(token, setToken);
  const loginComponent = (props) => (
    <Login {...props} uri="/login" auth={auth} />
  );

  const productListComponent = (props) => <ProductList auth={auth} />;

  return (
    <div className="flex flex-col min-h-screen h-full ">
      <Router>
        <Header userInfo={token} auth={auth} />
        <div className="flex-grow flex-shrink-0 p-4">
          <CartContextProvider>
            <Switch>
              <Route path="/" exact render={() => productListComponent()} />
              <Route
                path="/login"
                render={(props) =>
                  token ? productListComponent() : loginComponent(props)
                }
              />
              <Route
                path="/cart"
                render={(props) =>
                  token ? <Cart auth={auth} /> : loginComponent(props)
                }
              />
              <Route
                path="/orders"
                render={(props) =>
                  token ? <Orders auth={auth} /> : loginComponent(props)
                }
              />
              <Route
                path="/products/:id"
                render={() => <ProductDetail auth={auth} />}
              />
              <Route path="*" exact component={NotFound} />
            </Switch>
          </CartContextProvider>
        </div>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
