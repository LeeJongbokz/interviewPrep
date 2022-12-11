import { useState } from 'react';
import ExplainSectionCards from '../../../UI/ExplainSectionCards';
// import useHttpRequest from '../../../../hook/use-http';

const ReferenceItem = ({ namae, content, date, heartCnt:initHeartCnt }) => {
  const [favorite, setFavorite] = useState(false);
  const [heartCnt, setHeartCnt] = useState(initHeartCnt)

  // const { sendPostRequest } = useHttpRequest();

  const favoriteHandler = () => {
    console.log('FAV');
    setFavorite(true);
    setHeartCnt(prevState => prevState+1);
    
  };
  const unFavoriteHandler = () => {
    console.log('UN_FAV');
    setFavorite(false);
    setHeartCnt(prevState => prevState-1);
  };

  return (
    <ExplainSectionCards
      namae={namae}
      answer={content}
      heartCnt={heartCnt}
      availFav={true}
      favorite={favorite}
      favHandler={favoriteHandler}
      unFavHandler={unFavoriteHandler}
    />
  );
};

export default ReferenceItem;
