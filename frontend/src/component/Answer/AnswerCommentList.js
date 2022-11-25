import { useEffect, useRef, useState } from 'react';

import FormControl from '@mui/material/FormControl';
import ListItem from '@mui/material/ListItem';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Divider from '@mui/material/Divider';
import Typography from '@mui/material/Typography';

const AnswerCommentList = ({ answerId }) => {
  const [comments, setComments] = useState([]);

  useEffect(() => {
    const getCommentAPI = async () => {
      const response = await fetch(
        `https://react-post-de8f7-default-rtdb.firebaseio.com/interviewPrep/answerComment/${answerId}.json`
      );
      if (!response.ok) {
        console.log('ERR');
        return;
      }
      const commentData = await response.json();

      const loadedComments = [];

      for (const key in commentData) {
        loadedComments.push(commentData[key].comment);
      }
      setComments(loadedComments);
    };
    getCommentAPI();
  }, [answerId]);

  const commentRef = useRef();
  const submitHandler = async () => {
    const response = await fetch(
      `https://react-post-de8f7-default-rtdb.firebaseio.com/interviewPrep/answerComment/${answerId}.json`,
      {
        method: 'POST',
        body: JSON.stringify({ comment: commentRef.current.value }),
        Headers: {
          'Content-Type': 'application/json',
        },
      }
    );
    if (response.ok) {
      console.log('success');
    }
  };

  return (
    <>
      <Divider />
      <Typography textAlign="center" variant="h5" m={1}>Comments</Typography>
      {comments.map((val, index) => {
        return <ListItem sx={{ ml: 2}} key={index}>{val}</ListItem>;
      })}
      <FormControl
        variant="standard"
        margin="dense"
        sx={{ display: 'flex', flexDirection: 'row' }}
        fullWidth
      >
        <TextField
          multiline
          rows={2}
          // onChange={changeHandler}
          placeholder="답을 입력해주세요"
          inputProps={{
            maxLength: 50,
          }}
          sx={{ flex: 1 }}
          inputRef={commentRef}
          // helperText={`${answer.length}/50`}
        />
        <Button onClick={submitHandler} variant="outlined">
          등록
        </Button>
      </FormControl>
    </>
  );
};

export default AnswerCommentList;
