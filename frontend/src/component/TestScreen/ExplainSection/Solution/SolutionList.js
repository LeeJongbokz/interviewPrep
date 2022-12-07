import { useEffect, useState, useContext } from 'react';
import { useParams } from 'react-router-dom';
import { BACKEND_BASE_URL } from '../../../../global_variables';

import AuthContext from '../../../../store/auth-context';

import List from '@mui/material/List';

import LoadingSpinner from '../../../UI/LoadingSpinner';
import SolutionItem from './SolutionItem';
import { getAnswerList, setAnswerList } from '../../TestScreenVariables';

const SolutionList = () => {
  const { questionId } = useParams();
  const storedAnswerList = getAnswerList();
  const [answerArray, setAnswerArray] = useState(storedAnswerList);
  const [loading, setLoading] = useState(storedAnswerList.length < 1);

  const authCtx = useContext(AuthContext);
  // const navigate = useNavigate();

  useEffect(() => {
    // const multipleFetch = async () => {
    //   setLoading(true);
    //   let urls = [`${BACKEND_BASE_URL}/answer/solution/${questionId}/all`];

    //   const allResponses = await Promise.all(urls.map(url => fetch(url)));

    //   allResponses.forEach(async res => {
    //     if (!res.ok) {
    //       throw new Error('Error was occured!');
    //     }
    //     const data = await res.json();

    //     if (res.url.includes('answer/solution')) {
    //       setAnswerArray(data.data.content);
    //       setAnswerList(data.data.content);
    //     }
    //   });
    //   setLoading(false);
    // };
    const fetchSolutions = async () => {
      // setLoading(true);
      const response = await fetch(`${BACKEND_BASE_URL}/answer/solution/${questionId}/others`, {
        headers: {
          accessToken: authCtx.token,
          refreshToken: authCtx.refreshToken,
        },
      });

      if (!response.ok) {
        throw Error('Something Went Error');
      }

      const data = await response.json();
      setAnswerArray(data.data.content);
      setAnswerList(data.data.content);
      setLoading(false);
    };

    if (getAnswerList().length === 0) {
      fetchSolutions().catch(err => {
        console.log(err);
        setLoading(false);
      });
    }
  }, [questionId, authCtx.token, authCtx.refreshToken]);

  return (
    <>
      {loading && <LoadingSpinner />}
      {!loading && (
        <List sx={{ bgcolor: 'white' }}>
          {answerArray.length === 0 && '등록된 솔루션이 없습니다.'}
          {answerArray.map(item => {
            return (
              <SolutionItem
                key={item.answerId}
                answerId={item.answerId}
                name={item.name}
                answer={item.answer}
                heartCnt={item.heartCnt}
                // heart={heart}
              />
            );
          })}
        </List>
      )}
    </>
  );
};

export default SolutionList;
