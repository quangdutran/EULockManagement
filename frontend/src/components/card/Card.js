import React from "react";
import "./Card.css";

const Card = ({ imgSrc, title, id }) => {

    function handleOnclick(event) {
        event.preventDefault();
        window.location.href = "/" + id;
    }

  return (
    <div className="card-container">
      <div className="card" onClick={handleOnclick}>
        <div className="card-content">
          <div className="image-wrapper">
            <img src={imgSrc} alt={title} className="image" />
          </div>
          <p className="title">{title}</p>
        </div>
      </div>
    </div>
  );
};

export default Card;
