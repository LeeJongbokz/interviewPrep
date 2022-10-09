import styled from 'styled-components';

const Problem = ({ problem }) => {
  return (
    <Card>
      <p>
        <span>
          <p>분류: {problem[0]}</p>
          문제: {problem[1]}
        </span>
      </p>
      <p> 문제 난이도 : {problem[2]}</p>
      <p>푼 사람 수: {problem[3]}</p>
    </Card>
  );
};

export default Problem;

const Card = styled.div`
  margin: 30px auto;
  width: 60%;
  border: 3px solid;
  border-radius: 15px;
`;
