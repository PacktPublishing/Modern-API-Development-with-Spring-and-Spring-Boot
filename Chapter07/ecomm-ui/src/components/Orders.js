import { useEffect, useState } from "react";
import OrderClient from "../api/OrderClient";

const Orders = ({ auth }) => {
  const [orders, setOrders] = useState([]);

  const formatDate = (dt) => {
    return dt && new Date(dt).toLocaleString();
  };

  useEffect(() => {
    async function fetchOrders() {
      const client = new OrderClient(auth);
      const res = await client.fetch();
      if (res && res.success) {
        console.log(res.data);
        setOrders(res.data);
      }
    }
    fetchOrders();
  }, []);

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div className="flex flex-col">
        <div className="overflow-x-auto sm:-mx-6 lg:-mx-8">
          <div className="py-4 align-middle inline-block min-w-full sm:px-6 lg:px-8">
            <div className="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg">
              <table className="min-w-full divide-y divide-gray-200">
                <thead className="bg-gray-50">
                  <tr>
                    <th
                      scope="col"
                      className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                    >
                      Order Date
                    </th>
                    <th
                      scope="col"
                      className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                    >
                      Order Items
                    </th>
                    <th
                      scope="col"
                      className="pl-8 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
                    >
                      Status
                    </th>
                    <th
                      scope="col"
                      className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider"
                    >
                      Order Amount
                    </th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  {orders && orders.length < 1 ? (
                    <tr className="px-6 py-4 whitespace-nowrap">
                      Found zero order
                    </tr>
                  ) : (
                    orders?.map((order) => (
                      <tr>
                        <td className="px-6 py-4 whitespace-nowrap">
                          <div className="text-sm text-gray-900">
                            {formatDate(order?.date)}
                          </div>
                          <div className="text-sm text-gray-500">
                            Local Time
                          </div>
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap">
                          <div className="flex items-center">
                            <div className="">
                              <div className="text-sm font-medium text-gray-900">
                                {order?.items.map((o, idx) => (
                                  <div>
                                    <span className="text-sm text-gray-500">
                                      {idx + 1}.
                                    </span>{" "}
                                    {o.name}{" "}
                                    <span className="text-xs font-normal text-gray-500">
                                      (
                                      {o?.quantity +
                                        " x $" +
                                        o?.unitPrice?.toFixed(2)}
                                      )
                                    </span>
                                    <br />
                                  </div>
                                ))}
                              </div>
                            </div>
                          </div>
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap">
                          <span className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                            {order?.status}
                          </span>
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap text-right text-sm text-gray-500">
                          ${order?.total?.toFixed(2)}
                        </td>
                      </tr>
                    ))
                  )}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Orders;
