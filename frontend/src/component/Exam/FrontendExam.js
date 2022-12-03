import React, { useEffect, useState } from 'react';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import ContainerUI from '../UI/ContainerUI';

const FrontendExam = () => {

  const [exam, setExam] = useState('');

//   const submitHandler = async () => {
//     if (window.confirm('답안을 제출하시겠습니까?')) {
//       const bodyData = {
//         examId: examId,
//         memberId: memberId,
//         answers: answers
//         content: answerRef.current.value.slice(0, 50),
//       };
//       const response = await fetch(`http://52.202.27.18:8080//exam`, {
//         method: 'POST',
//         body: JSON.stringify(bodyData),
//         headers: {
//           'Content-Type': 'application/json',
//           accessToken: authCtx.token,
//           refreshToken: authCtx.refreshToken,
//         },
//       });
//       if (!response.ok) {
//         alert('오류가 발생했습니다. 다시 시도해주세요!');
//         return;
//       }
//       navigate('/test');
//       return;
//     }
//   };

  useEffect(() => {
    const fetchExam = async () => {
      const response = await fetch(`http://52.202.27.18:8080/exam`);

      if (!response.ok) {
        throw new Error('Some Thing Went Error');
      }
      const data = await response.json();
      setExam(data.data);
    };
    fetchExam().catch(err => {
      console.log(err);
    });
  }, []);

  return (
    <>
      {exam && (
        <ContainerUI>
          <Typography variant="h5" gutterBottom>
            {exam}
          </Typography>
        <div>문제를 물러오고 있습니다</div>
        <Button onClick='test' variant="outlined">
          제출
        </Button>
        </ContainerUI>
      )}
    </>
  );

};

export default FrontendExam;
