import { useEffect, useState } from 'react';

import useHttpRequest from '../../../../hook/use-http';

import List from '@mui/material/List';

import LoadingSpinner from '../../../UI/LoadingSpinner';
import SolutionItem from './SolutionItem';
import { getAnswerList, setAnswerList } from '../../TestScreenVariables';

const SolutionList = ({ questionId }) => {
  const storedAnswerList = getAnswerList();
  const [answerArray, setAnswerArray] = useState(storedAnswerList);

  const { isLoading, sendGetRequest } = useHttpRequest();
  useEffect(() => {
    const answerArrayHandler = data => {
      setAnswerArray(data.data.content);
      setAnswerList(data.data.content);
    };
    if (answerArray.length < 1) {
      sendGetRequest(`/answer/solution/${questionId}/others`, answerArrayHandler);
    }
  }, [sendGetRequest, answerArray.length, questionId]);

  return (
    <>
      {isLoading && <LoadingSpinner />}
      {!isLoading && (
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
