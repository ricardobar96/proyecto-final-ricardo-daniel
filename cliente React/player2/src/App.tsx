import React, { useState } from 'react';
import { Link, Route, BrowserRouter, Routes, Navigate, useNavigate } from 'react-router-dom';
import Login from './login/login';
import Home from './home/home';
import Register from './register/register';
import Games from './games/games';
import InfoGame from './infoGame/infoGame';
import ChatHome from './home/chat/chatHome';
import ReviewGame from './infoGame/reviewGame';
import ClueGame from './infoGame/clueGame';
import Profile from './profile/mainProfile/profile';
import GamesProfile from './profile/gamesProfile/gamesProfile';
import ReviewsProfile from './profile/reviewsProfile';
import SocialProfile from './profile/socialProfile';
import Settings from './profile/settings';
import CreateGame from './home/createGame';
import ModifyGame from './infoGame/modifyGame';

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
  return <Navigate to="/login" />
}

const App = () => {

  return (
    <BrowserRouter>
      <Routes>
      <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route path="/games" element={<Games/>} />
        <Route path="api/v0/videojuego/:id" element={<InfoGame/>} />

        <Route path="api/v0/usuario/:id" element={
          <RequireAuth >
            <Profile/>
          </RequireAuth>
        } />
        <Route path="api/v1/usuario/:id/gamesProfile" element={
          <RequireAuth >
            <GamesProfile/>
          </RequireAuth>
        } />
        <Route path="api/v1/usuario/:id/reviewsProfile" element={
          <RequireAuth >
            <ReviewsProfile/>
          </RequireAuth>
        } />
        <Route path="api/v1/usuario/:id/socialProfile" element={
          <RequireAuth >
            <SocialProfile/>
          </RequireAuth>
        } />
        <Route path="/chat" element={
          <RequireAuth >
            <ChatHome/>
          </RequireAuth>
        } />
        <Route path="api/v1/newReview/videojuego/:id" element={
          <RequireAuth >
            <ReviewGame/>
          </RequireAuth>
        } />
        <Route path="api/v1/newClue/videojuego/:id" element={
          <RequireAuth >
            <ClueGame/>
          </RequireAuth>
        } />
        <Route path="api/v1/usuario/:id/settings" element={
          <RequireAuth >
            <Settings/>
          </RequireAuth>
        } />
        <Route path="api/v1/createGame" element={
          <RequireAuth >
            <CreateGame/>
          </RequireAuth>
        } />
        <Route path="api/v2/modifyGame/videojuego/:id" element={
          <RequireAuth >
            <ModifyGame/>
          </RequireAuth>
        } />
      </Routes>
    </BrowserRouter>
  );
}

export default App;