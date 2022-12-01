import ContainerUI from '../UI/ContainerUI';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
const ExamList = () => {
  const navigate = useNavigate();

    return (
      <ContainerUI>
      <div>모의고사 페이지입니다</div>
      <Button variant="outlined" onClick={() => navigate('/exam/frontend')}>
        FRONTEND
      </Button>
      <Button variant="outlined" onClick={() => navigate('/exam/frontend')}>
        BACKEND
      </Button>
      </ContainerUI>
    );
  };
  
  export default ExamList;
  