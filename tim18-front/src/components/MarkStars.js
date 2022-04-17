import React from 'react';
import '../assets/styles/form.css';
import '../assets/styles/profile.css';
import PropTypes from 'prop-types';
import Star from './Star';

MarkStars.propTypes = {
    mark: PropTypes.number
  };

export default function MarkStars({mark}){
    return <span>
            <Star isColored={mark > 0.5 ? "coloredStar" : ""} />
            <Star isColored={mark > 1.5 ? "coloredStar" : ""} />
            <Star isColored={mark > 2.5 ? "coloredStar" : ""} />
            <Star isColored={mark > 3.5 ? "coloredStar" : ""} />
            <Star isColored={mark > 4.5 ? "coloredStar" : ""} />
        </span>
}

