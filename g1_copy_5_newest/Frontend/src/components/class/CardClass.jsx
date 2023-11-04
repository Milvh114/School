import React, { useEffect, useMemo, useState } from 'react'
import { useDispatch } from 'react-redux'
import { getUserById } from '../../features/user/userSlice.js'
import { getSubjectById } from '../../features/subject/subjectSlice.js'
import { Link, useLocation } from 'react-router-dom'
import { toggleClassDetail } from '../../features/class/classEntitySlice.js'

const CardClass = ({ classElement }) => {
  let status = ''
  const location = useLocation()
  useEffect(() => {
    localStorage.removeItem('current-class')
  }, [location])

  switch (classElement.status) {
    case 'PENDING':
      status = 'badge badge-info ml-2'
      break
    case 'ONGOING':
      status = 'badge badge-primary ml-2'
      break
    case 'COMPLETED':
      status = 'badge badge-success ml-2'
      break
    case 'CANCELLED':
      status = 'badge badge-danger ml-2'
      break
  }

  const dispatch = useDispatch()
  const [info, setInfo] = useState({
    manager: '',
    subject: ''
  })

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (typeof classElement.manager.id !== 'number') {
          return
        }
        const [response1, response2] =
          await Promise.all([
            dispatch(getUserById({ id: classElement.manager.id })),
            dispatch(getSubjectById({ id: classElement.subject.id }))
          ])

        setInfo((prevIncharge) => ({
          ...prevIncharge,
          manager: response1.payload,
          subject: response2.payload
        }))
      } catch (error) {
        // Handle errors here
      }
    }
    fetchData()
  }, [classElement.manager.id, classElement.subject.id])

  const memoizedManager = useMemo(() => info.manager, [info.manager])
  const memoizedSubject = useMemo(() => info.subject, [info.subject])
  return (
  <>
            <tr>
              <td>{classElement.id}</td>
              <td>{classElement.code}</td>
              <td>{memoizedSubject.code}</td>
              <td>{memoizedManager ? memoizedManager.fullName : ''}</td>
              <td>{classElement.status}</td>
              <td>
              
            <Link to={ '../class-detail' } 
            onClick={() => dispatch(toggleClassDetail(classElement))}
            className='btn btn-primary'><i
            className='fas fa-eye'></i></Link>
            <Link to={ `/class-edit/${ classElement.id }` } className='btn btn-primary'
            style={{margin:'2px'}}><i
            className='fas fa-pen'></i></Link>
            {/* tự css cho cái dưới đi nhé :)) */}
            <Link to={ `/class-edit/${ classElement.id }` } className='btn btn-primary'><i
            className='fas fa-pen'></i></Link>  

            </td>

              
            </tr>
  </>
            
   
    
  )
}

export default CardClass
