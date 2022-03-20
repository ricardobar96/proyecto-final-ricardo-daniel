import React, { useState } from 'react';
import { Link, Route, BrowserRouter, Routes, Navigate, useNavigate } from 'react-router-dom';
import Login from './login/login';
import Home from './home/home';
import Register from './register/register';
import Games from './games/games';
import InfoGame from './infoGame/infoGame';
import ChatHome from './home/chat/chatHome';

interface IProps { }
interface IState { }

interface IProps {
  children: JSX.Element;
}
export const RequireAuth = ({ children }: IProps) => {
  let autorizado = localStorage.getItem("token");
  if (autorizado) {
    return children
  }
  return <Navigate to="/" />
}

const App = () => {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route path="/home" element={
          <RequireAuth >
            <Home />
          </RequireAuth>
        } />
        <Route path="/games" element={
          <RequireAuth >
            <Games/>
          </RequireAuth>
        } />
        <Route path="api/v1/videojuego/:id" element={
          <RequireAuth >
            <InfoGame/>
          </RequireAuth>
        } />
        <Route path="/chat" element={
          <RequireAuth >
            <ChatHome/>
          </RequireAuth>
        } />
      </Routes>
    </BrowserRouter>
  );
}

export default App;