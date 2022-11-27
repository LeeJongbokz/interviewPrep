import React, { useState } from 'react';
import { useCookies } from 'react-cookie';

const AuthContext = React.createContext({
  token: '',
  refreshToken : '',
  isLoggedIn: false,
  login: () => {},
  logout: () => {},
});

export const AuthContextProvider = props => {
  const [cookies, setCookie, removeCookie] = useCookies(['interviewPrep']);

  let initialToken = cookies.usertoken;
  let initialReftoken = cookies.refreshtoken;

  const [token, setToken] = useState(initialToken);
  const [refreshToken, setRefreshToken] = useState(initialReftoken);
  const userIsLoggedIn = !!token;

  const loginHandler = async (id, pw) => {
    try {
      const loginData = {
        email: id,
        password: pw,
      };
      const response = await fetch(`http://52.202.27.18:8080/members/login`, {
        method: 'POST',
        body: JSON.stringify(loginData),
        headers: {
          'Content-Type': 'application/json',
        },
      });
      if (!response.ok) {
        throw new Error();
      }
      const tokenData = await response.json();
      if (tokenData?.accessToken) {
        const expires = new Date();
        expires.setUTCDate(expires.setUTCDate() + 14);
        setCookie('usertoken', tokenData.accessToken, {
          path: '/',
          expires: expires,
        });
        setCookie('refreshtoken', tokenData.refreshToken, {
          path: '/',
          expires: expires
        });
        setToken(tokenData.accessToken);
        setRefreshToken(tokenData.refreshToken);
        return { error: false, success: true };
      } else {
        return { error: false, success: false };
      }
    } catch (e) {
      return { error: true, success: false };
    }
  };

  const logoutHandler = () => {
    setToken(null);
    removeCookie('refreshtoken');
    return;
  };

  const contextValue = {
    token: token,
    refreshToken: refreshToken,
    isLoggedIn: userIsLoggedIn,
    login: loginHandler,
    logout: logoutHandler,
  };

  return <AuthContext.Provider value={contextValue}>{props.children}</AuthContext.Provider>;
};

export default AuthContext;
