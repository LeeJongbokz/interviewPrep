import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

import Typography from '@mui/material/Typography';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import ContainerUI from '../UI/ContainerUI';
import LoadingSpinner from '../UI/LoadingSpinner';

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
          setAnswerArray(data.content);
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
            {answerArray.length === 0 && "등록된 답변이 없습니다."}
            {answerArray.map(item => {
              return (
                <ListItem alignItems="flex-start" key={item.id}>
                  <ListItemText sx={{ display: 'inline' }} primary={item.answer} />
                </ListItem>
              );
            })}
          </List>
        </>
      )}
    </ContainerUI>
  );
};

export default AnswerList;
