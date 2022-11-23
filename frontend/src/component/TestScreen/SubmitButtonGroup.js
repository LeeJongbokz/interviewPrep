import { Link } from 'react-router-dom';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
const SubmitButtonGroup = ({ testId, submitHandler }) => {
  return (
    <Stack direction="row" spacing={1}>
      <Button variant="contained" onClick={submitHandler} m={1}>
        제출하기
      </Button>
      <Button component={Link} to={`/answer/${testId}`} variant="outlined" m={1}>
        답변보기
      </Button>
    </Stack>
  );
};
export default SubmitButtonGroup;
