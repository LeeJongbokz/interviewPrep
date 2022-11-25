import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

import Typography from '@mui/material/Typography';
import List from '@mui/material/List';

import ContainerUI from '../UI/ContainerUI';
import LoadingSpinner from '../UI/LoadingSpinner';
import AnswerItem from './AnswerItem';

const ANSWER_DATA = [
  // Todo 날짜 추가.
  {
    answerId: 2, //답안 id
    answer: 'answer2', //답안
    name: 'jin', //답안을 작성한 사람의 이름
    heartCnt: 5, //좋아요 개수
  },
  {
    answerId: 1,
    answer: 'answer1',
    name: 'jin',
    heartCnt: 3,
  },
];

const AnswerList = () => {
  const { id: questionId } = useParams();
  const [answerArray, setAnswerArray] = useState([]);
  const [question, setQuestion] = useState('');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const multipleFetch = async () => {
      setLoading(true);
      let urls = [
        `http://52.202.27.18:8080/question/single/${questionId}`,
        `http://52.202.27.18:8080/answer/solution/${questionId}/all`,
      ];

      const allResponses = await Promise.all(urls.map(url => fetch(url)));

      allResponses.forEach(async res => {
        if (!res.ok) {
          throw new Error('Error was occured!');
        }
        const data = await res.json();

        if (res.url.includes('question/single')) {
          setQuestion(data.title);
        } else if (res.url.includes('answer/solution')) {
          // Todo 더미데이터를 response 데이터로 변경
          setAnswerArray(ANSWER_DATA);
        }
      });
      setLoading(false);
    };
    multipleFetch().catch(err => {
      console.log(err);
      setLoading(false);
    });
  }, [questionId]);

  return (
    <ContainerUI>
      {loading && <LoadingSpinner />}
      {!loading && (
        <>
          <Typography variant="h5" gutterBottom>
            "{question}" 의 답변.
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
                />
              );
            })}
          </List>
        </>
      )}
    </ContainerUI>
  );
};

export default AnswerList;
