import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { BACKEND_BASE_URL } from '../../global_variables';

import Typography from '@mui/material/Typography';
import List from '@mui/material/List';

import LoadingSpinner from '../UI/LoadingSpinner';
import AnswerItem from './AnswerItem';
import { getAnswerList, setAnswerList } from '../TestScreen/TestScreenVariables';

const AnswerList = () => {
  const { questionId } = useParams();
  const [answerArray, setAnswerArray] = useState(getAnswerList());
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const multipleFetch = async () => {
      setLoading(true);
      let urls = [
        `${BACKEND_BASE_URL}/answer/solution/${questionId}/all`,
      ];

      const allResponses = await Promise.all(urls.map(url => fetch(url)));

      allResponses.forEach(async res => {
        if (!res.ok) {
          throw new Error('Error was occured!');
        }
        const data = await res.json();

        if (res.url.includes('answer/solution')) {
          setAnswerArray(data.data.content);
          setAnswerList(data.data.content);
        }
      });
      setLoading(false);
    };
    if(getAnswerList().length === 0){
      multipleFetch().catch(err => {
        console.log(err);
        setLoading(false);
      });
    }
  }, [questionId]);

  return (
    <>
      {loading && <LoadingSpinner />}
      {!loading && (
        <>
          <Typography variant="h5" gutterBottom>
            Answer
          </Typography>
          <List sx={{ bgcolor: 'white' }}>
            {answerArray.length === 0 && '등록된 답변이 없습니다.'}
            {answerArray.map(item => {
              return (
                <AnswerItem
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
        </>
      )}
    </>
  );
};

export default AnswerList;
