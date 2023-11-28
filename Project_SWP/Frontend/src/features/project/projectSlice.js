import {createAsyncThunk, createSlice} from '@reduxjs/toolkit'
import {
  getJWTFromLocalStorage,
} from '../../utils/localStorage.js'
import {
  addProjectThunk, deleteProjectByIdThunk,
  getAllProject, getProjectByIdThunk, updateProjectThunk,
} from './projectThunk.js'

const initialState = {
  isLoading: false,
  allSystemSetting: [],
  totalSystemSetting: 0,
  numOfPages: 1,
  page: 1,
  isFormDisplay: false,
  currentSystemSettingForm: '',
  token: getJWTFromLocalStorage(),
}
export const getListOfProject = createAsyncThunk(
  'project/getListOfProject',
  async ( project, thunkAPI ) => {
    return getAllProject( `/projects?page=${ project.currentPage }&size=${ project.pageSize }`, thunkAPI )
  },
)
export const getListOfProjectByClass= createAsyncThunk(
  'project/getListOfProjectByClass',
  async ( project, thunkAPI ) => {
    return getAllProject( `/projects/class?page=${ project.currentPage }&size=${ project.pageSize }&classID=${ project.classDto.id }`, thunkAPI )
  },
)
export const getListOfProjectByClassWithoutPaging = createAsyncThunk(
  'project/getListOfProjectByClassWithoutPaging',
  async ( project, thunkAPI ) => {
    return getAllProject( `/projects/class/all?classID=${ project.classDto.id }`, thunkAPI )
  },
)
export const getProjectById = createAsyncThunk(
  'project/getProjectById',
  async ( project, thunkAPI ) => {
    return getProjectByIdThunk( '/projects/' + project.id, thunkAPI )
  },
)
export const addProject = createAsyncThunk(
  'project/addProject',
  async ( project, thunkAPI ) => {
    return addProjectThunk( '/projects', project, thunkAPI )
  },
)

export const updateProject = createAsyncThunk(
  'project/updateProject',
  async ( project, thunkAPI ) => {
    return updateProjectThunk( '/projects/' + project.id, project, thunkAPI )
  },
)
export const deleteProjectById = createAsyncThunk(
  'project/deleteProjectById',
  async ( project, thunkAPI ) => {
    return deleteProjectByIdThunk( '/projects/' + project.id, thunkAPI )
  },
)
const projectSlice = createSlice( {
  name: 'project',
  initialState,
  reducers: {
    toggleAdd: ( state ) => {
      state.isFormDisplay = !state.isFormDisplay
    },
  },
  extraReducers: ( builder ) => {
    builder
      .addCase( getListOfProject.fulfilled, ( state, {payload} ) => {
        state.isLoading = false
        state.allSubject = payload.content
      } )
      .addCase( getListOfProject.pending, ( state ) => {
        state.isLoading = true
      } )
      .addCase( getListOfProject.rejected, ( state ) => {
        state.isLoading = false
      } )
      .addCase( getListOfProjectByClass.fulfilled, ( state, {payload} ) => {
        state.isLoading = false
        state.allSubject = payload.content
      } )
      .addCase( getListOfProjectByClass.pending, ( state ) => {
        state.isLoading = true
      } )
      .addCase( getListOfProjectByClass.rejected, ( state ) => {
        state.isLoading = false
      } )
    //   .addCase( getListOfSystemSettingWithoutPaging.fulfilled, ( state, {payload} ) => {
    //     state.isLoading = false
    //     state.allSubject = payload.content
    //   } )
    //   .addCase( getListOfSystemSettingWithoutPaging.pending, ( state ) => {
    //     state.isLoading = true
    //   } )
    //   .addCase( getListOfSystemSettingWithoutPaging.rejected, ( state ) => {
    //     state.isLoading = false
    //   } )
      .addCase( getProjectById.fulfilled, ( state ) => {
        state.isLoading = false
      } )
      .addCase( getProjectById.pending, ( state ) => {
        state.isLoading = true
      } )
      .addCase( getProjectById.rejected, ( state ) => {
        state.isLoading = false
      } )
      .addCase( addProject.fulfilled, ( state ) => {
        state.isLoading = false
      } )
      .addCase( addProject.pending, ( state ) => {
        state.isLoading = true
      } )
      .addCase( addProject.rejected, ( state ) => {
        state.isLoading = false
      } )
      .addCase( updateProject.fulfilled, ( state ) => {
        state.isLoading = false
      } )
      .addCase( updateProject.pending, ( state ) => {
        state.isLoading = true
      } )
      .addCase( updateProject.rejected, ( state ) => {
        state.isLoading = false
      } )
      .addCase( deleteProjectById.fulfilled, ( state ) => {
        state.isLoading = false
      } )
      .addCase( deleteProjectById.pending, ( state ) => {
        state.isLoading = true
      } )
      .addCase( deleteProjectById.rejected, ( state ) => {
        state.isLoading = false
      } )
  },
} )
export const {
  toggleAdd,
} = projectSlice.actions
export default projectSlice.reducer
