import React, { useEffect, useState } from 'react'
import { Tabs, Tab, Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'react-bootstrap';
import { useDispatch } from 'react-redux'
import {
  updateProject
} from '../../features/project/projectSlice.js'
import { toast } from 'react-toastify'
import { Link } from 'react-router-dom';
import { getWishList } from '../../features/user/userSlice.js'

const CardProject = ({ project }) => {
  const dispatch = useDispatch()
  const [key, setKey] = useState('Info');
  const [isEnable, setIsEnable] = useState(project.isActive)
  // const [editProject, setEditProject] = useState( project )
  const [isOpen, setIsOpen] = useState(null)
  const [show, setShow] = useState(false);
  const [wishList, setWishList] = useState([])
  const [members, setMembers] = useState(project.members.map(mem => ({id: mem.id})))
  const listMem = project.members

  // useEffect(() => {
  //   setWishList
  // }, [show])

  const handleActive = async () => {

    let confirm = prompt('Are you sure ' + `${!isEnable ? 'ACTIVE' : 'DEACTIVE'}` + ' ? (Yes/No)')
    if (confirm === null) {
      confirm = ''
    }
    if (confirm.toLocaleUpperCase() === 'YES') {
      const newIsEnable = !isEnable
      setIsEnable(newIsEnable)
      project.isActive = newIsEnable
      console.log(project)
      // let leadId
      // project.leader === undefined ? leadId = null : leadId = project.leader.id
      const response = await dispatch(
        updateProject({
          ...project,
          // id: project.id,
          // classDto: {
          //   id: project.classDto.id
          // },
          // mentor: {
          //   id: project.mentor.id
          // },
          // leader: {
          //   id: leadId
          // },
          // isActive: project.isActive,
          // projectName: project.projectName,
          // groupName: project.groupName,
          // title: project.title,
          // description: project.description
        }),
      )
      response
      // if (response.type.includes('fulfilled')) {
        toast.success('Successfully!')
      // }
    }
    else {
      toast.error('Failed, your input is Wrong. Check it !')
      // alert('Wrong')
    }
  }

  const handleDropdown = (id) => {
    if (id !== isOpen) {
      setIsOpen(id);
    }
    if (id === isOpen) {
      setIsOpen(null)
    }
  }

  const handleMenber = async (action, id) => {
    if (action === 'Change') {
      project.leader.id = id
      const response = await dispatch(
        updateProject({
          ...project,
          // id: project.id,
          // classDto: {
          //   id: project.classDto.id
          // },
          // mentor: {
          //   id: project.mentor.id
          // },
          leader: {
            id: id
          },
          // isActive: project.isActive,
          // projectName: project.projectName,
          // groupName: project.groupName,
          // title: project.title,
          // description: project.description
        }),
      )
      if (response.type.includes('fulfilled')) {
        toast.success('Successfully!')
        setIsOpen(null)
      }
      else {
        toast.error('Failed, your input is Wrong. Check it !')
        // alert('Wrong')
      }
    }
  }


  const handleClose = () => setShow(false);
  const handleShow = async () => {
    setShow(true)
    await dispatch(getWishList())
      .then((response) => {
        setWishList(response.payload)
      })
  };

  const handleAddStudent = async (member) => {
    // const isListMem = members.includes(id)
    listMem.push(member)
    project.members = listMem
    setWishList(wishList.filter(mem => mem.id != member.id))
    console.log(project.members)
  };

  const handleSubmitModal = async () => {
    console.log(project.members.map(mem => ({id: mem.id})))
    // setWishList()
    const response = await dispatch(
      updateProject({
        ...project,
        members: project.members.map(mem => ({id: mem.id})),
      }),
    )
    console.log(response)
    if (response.type.includes('fulfilled')) {
      toast.success('Successfully!')
      // setIsOpen(null)
    }
    else {
      toast.error('Failed, your input is Wrong. Check it !')
      // alert('Wrong')
    }
    setShow(false)
  };


  // console.log(project.members)
  // console.log(listMem)

  return (

    <div className="col-12 col-md-6 col-lg-4 d-flex">
      <div className="card flex-fill">

        <Tabs defaultActiveKey="Info"
          id="uncontrolled-tab-example"
          onSelect={(k) => setKey(k)}
          activeKey={key}
          fill
        >
          <Tab eventKey="Info" title="Info" tabClassName="w-100 " className='ml-3 mr-2 mb-3' >
            <h5 className="card-title">{`${project.classDto.code} - ${project.projectName} - ${project.groupName}`}
              <small>
                <span className={project.isActive ? "badge badge-success ml-2" : "badge badge-danger ml-2"}>
                  {project.isActive ? "Active" : "Deactive"}
                </span>
              </small>
            </h5>
            <p className="card-text">
              {project.title}
              <br />
              <small>Description: {project.description}</small>
            </p>

            <div className='mr-3 mb-3' style={{ textAlign: 'center' }}>
              <div className='btn-group btn-toggle mr-2'>
                <button className={isEnable ? 'btn btn-xs btn-success' : 'btn btn-xs btn-danger '}
                  onClick={handleActive} value={isEnable}>{isEnable ? 'ON' : 'OFF '}
                </button>
              </div>
              <Link className="btn btn-primary" to={'/project-detail/' + project.id}>View Details</Link>
            </div>

            <div className="card-footer text-muted">
              <p className={'progress-info'}>
                <span>Progress</span>
                <span>100%</span>
              </p>
              <div className="progress progress-xs">
                <div
                  className="progress-bar bg-warning"
                  role="progressbar"
                  style={{ width: '100%' }}
                  aria-valuenow={75}
                  aria-valuemin={0}
                  aria-valuemax={100}
                />
              </div>
            </div>
          </Tab>

          <Tab eventKey="Menbers" title="Members" tabClassName="w-100" className='ml-1 mr-2'>

            <ul className="list-group list-group-flush">
              {project.members.map(m => (
                <li key={m.id} className="list-group-item">
                  <div className="row">
                    <div className="col-11">
                      {m.fullName}<small> - {m.email}</small>
                      {project.leader.id === m.id ? <i className="fa-solid fa-crown ml-3"></i> : ''}
                    </div>
                    <div className="col-1">

                      <button className="btn btn-light dropdown" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false" onClick={() => handleDropdown(m.id)}>
                        <i className="fa fa-ellipsis-v"></i>
                      </button>
                    </div>
                  </div>
                  <ul className={`dropdown-menu ${isOpen === m.id ? 'show' : ''}`} aria-labelledby="dropdownMenuLink" style={{ left: 'auto', right: 0 }} >
                    <li><button className="dropdown-item" onClick={() => handleMenber('Change', m.id)} >Change leader</button></li>
                    <li><button className="dropdown-item" onClick={() => handleMenber('Delete', m.id)} >Delete</button></li>
                    <li><button className="dropdown-item" onClick={() => handleMenber('Move')} >Move to</button></li>
                  </ul>
                </li>
              ))}
            </ul>
            <div className='mb-3' style={{ textAlign: 'center' }}>

              {/* <!-- Button trigger modal --> */}
              <Button variant="primary" onClick={handleShow}>
                Add member
              </Button>

              {/* <!-- Modal --> */}
              <Modal show={show} onHide={handleClose} animation={false} aria-labelledby="contained-modal-title-vcenter" size='lg'
                centered>
                <Modal.Header closeButton>
                  <Modal.Title id='contained-modal-title-vcenter'>Wish List</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                  <ul>
                    <h6>Project members:</h6>
                    {project.members.map(m => (
                      <li key={m.id} className="list-group-item">
                        <div className="row">
                          <div className="col-11">
                            {m.fullName}<small> - {m.email}</small>
                            {project.leader.id === m.id ? <i className="fa-solid fa-crown ml-3"></i> : ''}
                          </div>
                          <div className="col-1">

                            <button className="btn btn-light dropdown" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false" onClick={() => handleDropdown(m.id)}>
                              <i className="fa fa-ellipsis-v"></i>
                            </button>
                          </div>
                        </div>
                        <ul className={`dropdown-menu ${isOpen === m.id ? 'show' : ''}`} aria-labelledby="dropdownMenuLink" style={{ left: 'auto', right: 0 }} >
                          <li><button className="dropdown-item" onClick={() => handleMenber('Change', m.id)} >Change leader</button></li>
                          <li><button className="dropdown-item" onClick={() => handleMenber('Delete', m.id)} >Delete</button></li>
                          <li><button className="dropdown-item" onClick={() => handleMenber('Move')} >Move to</button></li>
                        </ul>
                      </li>
                    ))}
                    <h6>WishList:</h6>
                    {wishList.map(mem => (
                      <li key={mem.id} className="list-group-item">
                        <div className="row">
                          <div className="col-11">
                            {mem.fullName} - {mem.email}
                          </div>
                          <div className="col-1">
                            <button className="btn btn-light" role="button" onClick={() => handleAddStudent(mem)}>
                              <i className="fas fa-plus"></i>
                            </button>
                          </div>
                        </div>

                      </li>
                    ))}
                  </ul>

                </Modal.Body>
                <Modal.Footer>
                  <Button variant="secondary" onClick={handleClose}>
                    Close
                  </Button>
                  <Button variant="primary" onClick={handleSubmitModal}>
                    Save Changes
                  </Button>
                </Modal.Footer>
              </Modal>

            </div>
          </Tab>
          <Tab eventKey="Teacher" title="Teacher" tabClassName="w-100 " className='ml-3 mr-2 mb-3'>
            <ul className="list-group list-group-flush">

              <li className="list-group-item">
                <span className="title-span">Creator : </span>
                <span className="info-span">{project.creator.fullName}</span><br />
                <span className="title-span">Contact : </span>
                <span className="info-span">{project.creator.email} - {project.creator.phone}</span>
              </li>
              <li className="list-group-item">
                <span className="title-span">Mentor : </span>
                <span className="info-span">{project.mentor.fullName}</span><br />
                <span className="title-span">Contact : </span>
                <span className="info-span">{project.creator.fullName} - {project.mentor.phone}</span>
              </li>
            </ul>

            <div className='mb-3' style={{ textAlign: 'center' }}>
              <a className="btn btn-primary" href="#" >Edit Mentor</a>
            </div>
          </Tab>
        </Tabs>

      </div>
    </div>
  )
}

export default CardProject

{/* <div className="card-header">
          <h5 className="card-title mb-1 card-project">{project.projectName + " - " + project.groupName}</h5>
          <span className={project.isActive ? "badge badge-success ml-2" : "badge badge-danger ml-2"}>{project.isActive ? "Active" : "Deactive"}</span>
        </div> */}
{/* <div className="card-body">
          <p className="card-text">
            {project.title}
          </p>
          <div className="avatar-group">
            <div className="avatar">
              <img
                className="avatar-img rounded-circle border border-white"
                alt="User Image"
              />
            </div>
            <div className="avatar">
              <img
                className="avatar-img rounded-circle border border-white"
                alt="User Image"
              />
            </div>
            <div className="avatar">
              <img
                className="avatar-img rounded-circle border border-white"
                alt="User Image"
              />
            </div>
            <div className="avatar">
              <span className="avatar-title rounded-circle border border-white">
                CF
              </span>
            </div>
          </div>
          <br />
          <a className="btn btn-primary" href="#">
            Go somewhere
          </a>
        </div> */}