import { useState } from "react";

export default function useToken() {
  const getToken = () => {
    const tokenResponse = localStorage.getItem("tokenResponse");
    const userInfo = tokenResponse ? JSON.parse(tokenResponse) : "";
    return userInfo;
  };

  const [token, setToken] = useState(getToken());

  const saveToken = (tokenResponse) => {
    localStorage.setItem("tokenResponse", JSON.stringify(tokenResponse));
    setToken(tokenResponse);
  };

  return {
    setToken: saveToken,
    token,
  };
}
