const express = require('express');
const app = express();
const http = require('http');
const cors = require('cors');
const { Server } = require("socket.io");
app.use(cors());

const server = http.createServer(app);

const io = new Server(server, {
  cors: {
    origin: "http://localhost:3000",
    methods: ["GET", "POST"]
  },

});

io.on("connection", (socket) => {
  console.log(`Usuario conectado: ${socket.id}`);
  socket.on("join_room", (data) => {
    socket.join(data);
    console.log("Conectado a:" + data);
  });
  socket.on("send_message", (mensaje)=> {
    console.log(mensaje.contenido);
    console.log(mensaje.room);
    socket.to(mensaje.room).emit("receive_message", mensaje);
  });
  socket.on("disconnect", () => {
    console.log("Usuario desconectado", socket.id);
  });
});

server.listen(3001, () => {
  console.log("Server funciona");
});