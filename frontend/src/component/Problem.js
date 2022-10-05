import styled from 'styled-components';

const Problem = ({ problem }) => {
  return (
    <Tbody>
      <Trow>
        <td>{problem[0]}</td>
      </Trow>
      <Trow>
        <td style={{ minWidth: '150PX' }}>{problem[1]}</td>
      </Trow>
      <Trow>
        <td style={{ minWidth: '150PX' }}>{problem[2]}</td>
      </Trow>
      <Trow>
        <td style={{ minWidth: '150PX' }}>{problem[3]}</td>
      </Trow>
    </Tbody>
  );
};

export default Problem;

const Tbody = styled.tbody`
  display: flex;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  background-color: #ffffff;
  font-size: 0.875rem;
  line-height: 1.25rem;
  justify-content: space-between;
  align-items: center;
  width: 100%;
`;
const Trow = styled.tr`
  display: flex;
  justify-content: center;
  flex: 1 1 0%;

  min-width: 150px;
`;
