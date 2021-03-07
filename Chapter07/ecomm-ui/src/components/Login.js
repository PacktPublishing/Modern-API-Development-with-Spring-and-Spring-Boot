import { useHistory } from "react-router-dom";
import { useState } from "react";
import PropTypes from "prop-types";

const Login = ({ uri, auth }) => {
  const [username, setUserName] = useState();
  const [password, setPassword] = useState();
  const [errMsg, setErrMsg] = useState();
  const history = useHistory();

  const cancel = () => {
    const l = history.length;
    l > 2 ? history.goBack() : history.push("/");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const res = await auth.loginUser({
      username,
      password,
    });
    if (res && res.success) {
      setErrMsg(null);
      history.push(uri ? uri : "/");
    } else {
      setErrMsg(
        res && typeof res === "string" ? res : "Invalid Username/Password"
      );
    }
  };
  return (
    <div className="fixed z-10 inset-0 overflow-y-auto">
      <div className="flex items-center justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
        <div className="fixed inset-0 transition-opacity" aria-hidden="true">
          <div className="absolute inset-0 bg-gray-500 opacity-75"></div>
        </div>

        {/* To trick the browser into centering the modal */}
        <span
          className="hidden sm:inline-block sm:align-middle sm:h-screen"
          aria-hidden="true"
        >
          &#8203;
        </span>

        <div
          className="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:align-middle sm:max-w-lg sm:w-full"
          role="dialog"
          aria-modal="true"
          aria-labelledby="modal-headline"
        >
          <div className="bg-gray-50 px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
            <div className="sm:flex sm:items-start">
              <div className="flex items-center justify-center  py-12 px-10 sm:px-14 lg:px-16">
                <div className="max-w-md w-full space-y-8">
                  <h2 className="text-center text-3xl font-extrabold text-gray-900">
                    Sign in to your account
                  </h2>
                  <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
                    <div className="rounded-md shadow-sm -space-y-px">
                      <div>
                        <span
                          className="text-red-500"
                          style={{ dispay: errMsg ? "block" : "none" }}
                        >
                          {errMsg}
                        </span>
                        <label htmlFor="username" className="sr-only">
                          Username
                        </label>
                        <input
                          id="username"
                          name="username"
                          type="username"
                          autoComplete="username"
                          required
                          className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                          placeholder="Username"
                          onChange={(e) => setUserName(e.target.value)}
                        />
                      </div>
                      <div>
                        <label htmlFor="password" className="sr-only">
                          Password
                        </label>
                        <input
                          id="password"
                          name="password"
                          type="password"
                          autoComplete="current-password"
                          required
                          className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                          placeholder="Password"
                          onChange={(e) => setPassword(e.target.value)}
                        />
                      </div>
                    </div>

                    <div className="flex items-center justify-between">
                      <div>
                        <button
                          type="submit"
                          className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                        >
                          <span className="absolute left-0 inset-y-0 flex items-center pl-3">
                            <svg
                              className="h-5 w-5 text-indigo-400 group-hover:text-indigo-300"
                              xmlns="http://www.w3.org/2000/svg"
                              viewBox="0 0 20 20"
                              fill="currentColor"
                              aria-hidden="true"
                            >
                              <path
                                fillRule="evenodd"
                                d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z"
                                clipRule="evenodd"
                              />
                            </svg>
                          </span>
                          <span className="flex items-center pl-6">
                            Sign in
                          </span>
                        </button>
                      </div>
                      <div className="text-sm">
                        <button
                          type="button"
                          onClick={cancel}
                          className="transition-colors hover:text-gray-900 font-medium duration-200"
                        >
                          Cancel
                        </button>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

Login.propTypes = {
  auth: PropTypes.object.isRequired,
};

export default Login;
