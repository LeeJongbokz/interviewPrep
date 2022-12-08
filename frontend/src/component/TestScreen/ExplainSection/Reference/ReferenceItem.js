import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';

import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';

const ReferenceItem = ({namae, content, date, heartCnt}) => {
  return (
    <Card eveluation={0} variant="outlined" sx={{ marginY: 2 }}>
      <CardContent>
        <Box padding={1} sx={{ display: 'flex', justifyContent: 'space-between' }}>
          <Typography component="div" sx={{ fontWeight: 'bold' }}>
            {namae}
          </Typography>
          <Typography>{date}</Typography>
        </Box>
        <Box padding={1}>
          <Typography>{content}</Typography>
        </Box>
      </CardContent>
      <CardActions disableSpacing sx={{ marginLeft: 1, color: 'text.secondary' }}>
        <KeyboardArrowUpIcon />
        <Typography>{heartCnt}</Typography>
        <KeyboardArrowDownIcon />
        <MoreHorizIcon />
      </CardActions>
    </Card>
  );
};

export default ReferenceItem;
