import ReferenceItem from './ReferenceItem';

const REFERENCE_LIST = [
  {
    id: 1,
    namae: 'alpha',
    content: 'Reference #1',
    date: '2022-12-08',
    heartCnt: 1,
  },
  {
    id: 2,
    namae: 'beta',
    content: 'Reference #2',
    date: '2022-12-09',
    heartCnt: 3,
  },
];

const ReferenceList = () => {
  return (
    <>
      {REFERENCE_LIST.map(item => (
        <ReferenceItem
          key={item.id}
          namae={item.namae}
          content={item.content}
          date={item.date}
          heartCnt={item.heartCnt}
        />
      ))}
    </>
  );
};

export default ReferenceList;
