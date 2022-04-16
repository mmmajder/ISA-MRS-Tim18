import Penalty from './Penalty';
import PropTypes from 'prop-types';

PenaltyInfo.propTypes = {
    penaltyCount: PropTypes.number
  };

export default function PenaltyInfo({penaltyCount}){
    return <span>
                <Penalty isColored={penaltyCount > 0.5 ? "coloredStar" : ""} />
                <Penalty isColored={penaltyCount > 1.5 ? "coloredStar" : ""} />
                <Penalty isColored={penaltyCount > 2.5 ? "coloredStar" : ""} />
            </span>
}