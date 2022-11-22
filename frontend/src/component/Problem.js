import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Tooltip from '@mui/material/Tooltip';
import { useNavigate } from 'react-router-dom';
const Problem = ({ problem }) => {
  const navigate = useNavigate();
  return (
    <Card variant="outlined" onClick={() => navigate(`/test/${problem.id}`)}>
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
      </CardContent>
    </Card>
  );
};

export default Problem;
