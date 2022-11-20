import { Link } from 'react-router-dom';
import Stack from '@mui/material/Stack';
import IconButton from '@mui/material/IconButton';

const IconGroup = () => {
  return (
    <Stack direction="row">
      <IconButton component={Link} to="#">
        <img src="./logo/google-logo.svg" alt="google" style={{ width: 30 }} />
      </IconButton>
      <IconButton component={Link} to="#">
        <img src="./logo/naver-logo.svg" alt="naver" style={{ width: 30 }} />
      </IconButton>
      <IconButton component={Link} to="#">
        <img src="./logo/kakaotalk-logo.svg" alt="kakaotalk" style={{ width: 30 }} />
      </IconButton>
    </Stack>
  );
};

export default IconGroup;
