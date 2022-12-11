import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';

import MoreHorizIcon from '@mui/icons-material/MoreHoriz';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import FavoriteIcon from '@mui/icons-material/Favorite';

const ExplainSectionCards = ({
  namae,
  answer,
  heartCnt,
  availFav = false,
  favorite,
  favHandler,
  unFavHandler,
}) => {
  const favButton = () => {
    if (availFav) {
      if (favorite) {
        return <FavoriteIcon sx={{ cursor: 'pointer' }} onClick={unFavHandler} />;
      } else {
        return <FavoriteBorderIcon sx={{ cursor: 'pointer' }} onClick={favHandler} />;
      }
    } else {
      return <Typography>추천 수 :</Typography>;
    }
  };

  return (
    <Card eveluation={0} variant="outlined" sx={{ marginY: 2 }}>
      <CardContent>
        <Box padding={1} sx={{ display: 'flex', justifyContent: 'space-between' }}>
          <Typography component="div" sx={{ fontWeight: 'bold' }}>
            {namae}
          </Typography>
          <Typography>XXXX-XX-XX</Typography>
        </Box>
        <Box padding={1}>
          <Typography>{answer}</Typography>
        </Box>
      </CardContent>
      <CardActions
        disableSpacing
        sx={{ marginLeft: 1, color: 'text.secondary', display: 'flex', gap: '5px' }}
      >
        {favButton()}
        <Typography>{heartCnt}</Typography>
        <MoreHorizIcon />
      </CardActions>
    </Card>
  );
};

export default ExplainSectionCards;
