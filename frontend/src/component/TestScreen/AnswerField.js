import { useState } from 'react';
import TextField from '@mui/material/TextField';
import FormControl from '@mui/material/FormControl';

const AnswerField = ({ answerRefIn: answerRef }) => {
  const [answer, setAnswer] = useState('');

  const changeHandler = e => {
    setAnswer(e.target.value);
  };

  return (
    <FormControl
      margin="dense"
      fullWidth
      variant="standard"
      sx={{ marginTop: '20px', marginBottom: '20px' }}
    >
      <TextField
        id="answer"
        multiline
        rows={3}
        onChange={changeHandler}
        placeholder="답을 입력해주세요"
        inputProps={{
          maxLength: 50,
        }}
        inputRef={answerRef}
        helperText={`${answer.length}/50`}
      />
    </FormControl>
  );
};

export default AnswerField;
