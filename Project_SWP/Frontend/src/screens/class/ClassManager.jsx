import HeaderContent from "../../components/common/layout/HeaderContent.jsx";
import React, { useEffect, useState } from "react";
import CardClass from "../../components/class/CardClass.jsx";
import { useDispatch, useSelector } from "react-redux";
import {
  getAllClasses,
  getAllClassesWithSubject,
  toggleAdd,
  togglePagination,
} from "../../features/class/classEntitySlice.js";

import CardForm from "../../components/class/CardForm.jsx";
import Pagination from "../../components/common/table/Pagination.jsx";

const ClassManager = () => {
  const prePage = "Dashboard";
  const [load, setLoad] = useState(false);
  const [classEntitys, setClassEntitys] = useState([]);
  const dispatch = useDispatch();
  const { pagination, isFormDisplay } = useSelector(store => store.classEntity)

  useEffect(() => {
    const fetchData = async () => {
      const subject = JSON.parse(localStorage.getItem("subject-classes"));
      if (subject != null) {
        await dispatch(
          getAllClassesWithSubject({
            id: subject.id,
            currentPage: pagination.currentPage - 1,
            pageSize: pagination.pageSize,
          })
        ).then(function (response) {
          setClassEntitys(response.payload.content);
          dispatch(
            togglePagination({
              currentPage: response.payload.page + 1,
              pageSize: response.payload.size,
              totalPages: response.payload.totalPages,
              last: response.payload.last,
            })
          );
        });
      } else {
        await dispatch(
          getAllClasses({
            currentPage: pagination.currentPage - 1,
            pageSize: pagination.pageSize,
          })
        ).then(function (response) {
          setClassEntitys(response.payload.content);
          dispatch(
            togglePagination({
              currentPage: response.payload.page + 1,
              pageSize: response.payload.size,
              totalPages: response.payload.totalPages,
              last: response.payload.last,
            })
          );
        });
      }
    };
    fetchData();
  }, [load, pagination.currentPage]);

  const paging = [];
  for (let i = 1; i <= pagination.totalPages; i++) {
    paging.push(i);
  }
  const handlePageNumber = (e) => {
    e.preventDefault()
    const pageNumber = e.currentTarget.getAttribute('id')
    dispatch(togglePagination({
      currentPage: pageNumber,
      pageSize: pagination.pageSize,
      totalPages: pagination.totalPages,
      last: pagination.last
    }))
  }


  const handleIncreaseOrDecrease = (e) => {
    e.preventDefault();
    const change = e.currentTarget.getAttribute("id");
    if (change.toLowerCase() === "next" && pagination.last === false) {
      const nextPage = pagination.currentPage + 1;
      dispatch(
        togglePagination({
          currentPage: nextPage,
          pageSize: pagination.pageSize,
          totalPages: pagination.totalPages,
          last: pagination.last,
        })
      );
    }
    if (change.toLowerCase() === "previous" && pagination.currentPage > 0) {
      const previousPage = pagination.currentPage - 1;
      dispatch(
        togglePagination({
          currentPage: previousPage,
          pageSize: pagination.pageSize,
          totalPages: pagination.totalPages,
          last: pagination.last,
        })
      );
    }
  };
  return (
    <>
      <HeaderContent
        pageTitle={"List of Class"}
        pageName={"List of Class"}
        prePage={prePage}
      />
      <div
        className={"mb-3"}
        style={{
          display: "flex",
          justifyContent: "space-between",
        }}
      >
         <Pagination page={pagination} paging={paging} handlePageNumber={handlePageNumber}
                    handleIncreaseOrdecrease={handleIncreaseOrDecrease}/>

        <button
          className={"btn btn-primary"}
          onClick={() => {
            dispatch(toggleAdd());
          }}
        >
          {isFormDisplay === true ? "Close Form " : "Add form "}
          {isFormDisplay === true ? (
            <i className="fas fa-close"></i>
          ) : (
            <i className="fas fa-plus"></i>
          )}
        </button>
      </div>
      <div className="row">
      <div className='col-sm-12'>
        <div className='card card-table'>
          <div className='card-body'>
            <div className='table-responsive'>
              <table className='table table-hover table-center mb-0 datatable'>
  <thead>
  <tr>
          <th>ID</th>
          <th>Class Name</th>
          <th>Subject</th>
          <th>Manager</th>
          <th>Status</th>
          <th>Action</th>
      </tr>
  </thead>
<tbody>
  {
    classEntitys.map(
      (el) =>(
        <CardClass key={el} classElement={el}/>
      )
    )
  }
</tbody>
         </table>

</div>
</div>
</div>
</div>

        <div style={{ position: "fixed", zIndex: "999" }}>
          <CardForm load={load} setLoad={setLoad} />
        </div>
      </div>
    </>
  );
};

export default ClassManager;
