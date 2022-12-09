import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';

const SubmissionItem = ({answerId, idx, date, answer, heartCnt}) => {
  return (
    <Card eveluation={0} variant="outlined" sx={{ marginY: 2 }}>
      <CardContent>
        <Box padding={1} sx={{ display: 'flex', justifyContent: 'space-between' }}>
        <Typography component="div" sx={{ fontWeight: 'bold' }}>
            답변 {idx}
          </Typography>
          <Typography>{date || "2022-XX-XX"}</Typography>
        </Box>
        <Box padding={1}>
          <Typography>{answer}</Typography>
        </Box>
      </CardContent>
      <CardActions disableSpacing sx={{ marginLeft: 1, color: 'text.secondary' }}>
        <Typography>추천 수 : {heartCnt}</Typography>
      </CardActions>
    </Card>
  );
};

export default SubmissionItem;
