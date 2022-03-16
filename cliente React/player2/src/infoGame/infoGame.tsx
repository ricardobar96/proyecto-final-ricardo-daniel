import "./infoGame.css";
import { VideogameAsset } from "@material-ui/icons";
import React, { useRef } from 'react';
import axios from 'axios';
import Topbar from '../topbar/topbar';

export default function infoGame() {
    return (
        <>
            <Topbar />
            <div className="infoGame">
                <div className="infoGameWrapper">
                    <VideogameAsset className="gameImage" />
                    <div className="desciption">
                        <h3 className="title">Descripción:</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras tincidunt laoreet elit vitae mattis.
                            Duis tincidunt venenatis magna, non mattis dui hendrerit vel. Vivamus eros elit, vehicula elementum
                            porta id, consectetur vitae risus. Mauris enim risus, luctus eget ornare sed, placerat ac libero.
                            Morbi nec malesuada velit, id eleifend tellus. Donec ipsum turpis, sodales eget dui ut, faucibus
                            lacinia nulla. Nam ultrices lobortis sapien in aliquet. Curabitur iaculis sapien vel ex convallis
                            ultricies. Donec egestas, quam vel accumsan elementum, dolor odio sodales risus, quis varius ipsum
                            lorem in lacus. Ut ut eleifend felis, quis placerat orci.
                        </p>
                    </div>
                    <div className='pistasWrapper'>
                        <h3 className="title">Pistas:</h3>
                        <ul className='pistasList'>
                            <li>
                                Curabitur iaculis sapien vel ex convallis
                                ultricies. Donec egestas, quam vel accumsan elementum, dolor odio sodales risus, quis varius ipsum
                                lorem in lacus. Ut ut eleifend felis, quis placerat orci.
                            </li>
                            <li>
                                Morbi nec malesuada velit, id eleifend tellus. Donec ipsum turpis, sodales eget dui ut, faucibus
                                lacinia nulla. Nam ultrices lobortis sapien in aliquet.
                            </li>
                        </ul>
                    </div>
                    <div className='reviewsWrapper'>
                        <h3 className="title">Reviews:</h3>
                        <ul className='reviewsList'>
                            <li>
                                Morbi nec malesuada velit, id eleifend tellus. Donec ipsum turpis, sodales eget dui ut, faucibus
                                lacinia nulla. Nam ultrices lobortis sapien in aliquet. Curabitur iaculis sapien vel ex convallis
                                ultricies. Donec egestas, quam vel accumsan elementum, dolor odio sodales risus, quis varius ipsum
                                lorem in lacus. Ut ut eleifend felis, quis placerat orci.
                            </li>
                            <li>
                                Morbi nec malesuada velit, id eleifend tellus. Donec ipsum turpis, sodales eget dui ut, faucibus
                                lacinia nulla. Nam ultrices lobortis sapien in aliquet. Curabitur iaculis sapien vel ex convallis
                                ultricies. Donec egestas, quam vel accumsan elementum, dolor odio sodales risus, quis varius ipsum
                                lorem in lacus. Ut ut eleifend felis, quis placerat orci.
                            </li>
                            <li>
                                Morbi nec malesuada velit, id eleifend tellus. Donec ipsum turpis, sodales eget dui ut, faucibus
                                lacinia nulla. Nam ultrices lobortis sapien in aliquet. Curabitur iaculis sapien vel ex convallis
                                ultricies. Donec egestas, quam vel accumsan elementum, dolor odio sodales risus, quis varius ipsum
                                lorem in lacus. Ut ut eleifend felis, quis placerat orci.
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </>
    )
}