import React from 'react';
import { Link } from 'react-router-dom';
import Typography from '@mui/material/Typography';
import ContainerUI from './UI/ContainerUI';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';

const Main = () => {
  return (
    <ContainerUI>
      <div style={{ textAlign: 'center' }}>
        <Typography sx={{ paddingTop: '25px' }} variant="h4" component="h1">
          당신의 기술 면접을 위한 최고의 솔루션
        </Typography>
        <Typography sx={{ paddingTop: '25px' }} variant="h5" component="h2">
          답안을 작성하세요. 최고의 멘토들이 피드백 드립니다.
        </Typography>
        <Typography sx={{ fontSize: 25 }}>
          서비스 첫 이용시 누구나 <strong>무료</strong>
        </Typography>
        <Box sx={{ marginTop: '50px', paddingBottom: '70px' }}>
          <Button component={Link} to="/test" variant="contained">
            지금 이용하기
          </Button>
        </Box>
      </div>
    </ContainerUI>
  );
};

export default Main;
