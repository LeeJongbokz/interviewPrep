import { useState } from 'react';
import ExplainSectionCards from '../../../UI/ExplainSectionCards';
import useHttpRequest from '../../../../hook/use-http';

const SolutionItem = ({ answerId, name, answer, heartCnt:initHeartCnt, heart }) => {
  const [favorite, setFavorite] = useState(heart);
  const [heartCnt, setHeartCnt] = useState(initHeartCnt)

  const { sendPostRequest } = useHttpRequest();

  const favoriteHandler = () => {
    console.log('FAV');
    setFavorite(true);
    setHeartCnt(prevState => prevState+1);
    sendPostRequest({
      endpoint:"/heart",
      bodyData: {
        answer: answerId
      }
    })
  };
  const unFavoriteHandler = () => {
    console.log('UN_FAV');
    setFavorite(false);
    setHeartCnt(prevState => prevState-1);
  };

  return (
    <ExplainSectionCards
      namae={name}
      answer={answer}
      heartCnt={heartCnt}
      availFav={true}
      favorite={favorite}
      favHandler={favoriteHandler}
      unFavHandler={unFavoriteHandler}
    />
  );
};

export default SolutionItem;
