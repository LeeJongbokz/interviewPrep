import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { Button } from '@mui/material';
import Tooltip from '@mui/material/Tooltip';
import { useNavigate } from 'react-router-dom';
const Problem = ({ problem }) => {
  const navigate = useNavigate();
  return (
    <Card variant="outlined">
      <CardContent>
        <Typography>{problem.id}번</Typography>
        <Tooltip title={problem.title || '-'} placement="bottom">
          <Typography
            sx={{
              display: '-webkit-box',
              overflow: 'hidden',
              WebkitBoxOrient: 'vertical',
              WebkitLineClamp: 2,
              height: '50px',
            }}
          >
            문제: {problem.title || '-'}
          </Typography>
        </Tooltip>
        <Typography>Level : {problem?.level || '-'}</Typography>
        <Typography>분류: {problem.type || '-'}</Typography>
        <Button size="small" color="primary" onClick={() => navigate(`/test/${problem.id}`)}>
          문제 풀기
        </Button>
      </CardContent>
    </Card>
  );
};

export default Problem;
