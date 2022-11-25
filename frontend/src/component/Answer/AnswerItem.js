import { useState } from 'react';
import AnswerCommentList from './AnswerCommentList';
import VerticalSquareButton from '../UI/VerticalSquareButton';

import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Typography from '@mui/material/Typography';
import Collapse from '@mui/material/Collapse';

import FavoriteIcon from '@mui/icons-material/Favorite';
import ChatIcon from '@mui/icons-material/Chat';

const AnswerItem = ({ answerId, name, answer, heartCnt }) => {
  const [commentSection, setCommentSection] = useState(false);

  const toggleCommentSection = () => {
    setCommentSection(prevState => !prevState);
  };
  const toggleHeartSection = () => {
    console.log('HEART BUTTON');
  };

  return (
    <>
      <ListItem>
        <ListItemText primary={name} secondary={<Typography>{answer}</Typography>} />
        <VerticalSquareButton onClick={toggleHeartSection}>
          <FavoriteIcon />
          <span>{heartCnt}</span>
        </VerticalSquareButton>
        <VerticalSquareButton onClick={toggleCommentSection}>
          <ChatIcon />
        </VerticalSquareButton>
      </ListItem>

      <Collapse in={commentSection} timeout="auto">
        <AnswerCommentList answerId={answerId} />
      </Collapse>
    </>
  );
};

export default AnswerItem;
