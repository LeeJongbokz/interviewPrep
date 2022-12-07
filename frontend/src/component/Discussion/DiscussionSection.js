import { Typography, Box, CardActions } from '@mui/material';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';

import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';

const DiscussionSection = () => {
  return (
    <>
      <Card eveluation={0} variant="outlined" sx={{ margin: 2 }}>
        <CardContent>
          <Box padding={1} sx={{ display: 'flex', justifyContent: 'space-between' }}>
            <Typography component="div" sx={{ fontWeight: 'bold' }}>
              member ID
            </Typography>
            <Typography>XXXX-XX-XX</Typography>
          </Box>
          <Box padding={1}>
            <Typography>Text Body</Typography>
          </Box>
        </CardContent>
        <CardActions disableSpacing sx={{marginLeft:1, color:"text.secondary"}} >
          <KeyboardArrowUpIcon />
          <Typography>0</Typography>
          <KeyboardArrowDownIcon />
          <MoreHorizIcon />
        </CardActions>
      </Card>
      <Card eveluation={0} variant="outlined" sx={{ margin: 2 }}>
        <CardContent>
          <Box padding={1} sx={{ display: 'flex', justifyContent: 'space-between' }}>
            <Typography component="div" sx={{ fontWeight: 'bold' }}>
              member ID
            </Typography>
            <Typography>XXXX-XX-XX</Typography>
          </Box>
          <Box padding={1}>
            <Typography>Text Body</Typography>
          </Box>
        </CardContent>
        <CardActions disableSpacing sx={{marginLeft:1, color:"text.secondary"}} >
          <KeyboardArrowUpIcon />
          <Typography>0</Typography>
          <KeyboardArrowDownIcon />
          <MoreHorizIcon />
        </CardActions>
      </Card>
    </>
  );
};

export default DiscussionSection;
