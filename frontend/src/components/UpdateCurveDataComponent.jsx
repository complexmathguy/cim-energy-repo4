import React, { Component } from 'react'
import CurveDataService from '../services/CurveDataService';

class UpdateCurveDataComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
        }
        this.updateCurveData = this.updateCurveData.bind(this);

    }

    componentDidMount(){
        CurveDataService.getCurveDataById(this.state.id).then( (res) =>{
            let curveData = res.data;
            this.setState({
            });
        });
    }

    updateCurveData = (e) => {
        e.preventDefault();
        let curveData = {
            curveDataId: this.state.id,
        };
        console.log('curveData => ' + JSON.stringify(curveData));
        console.log('id => ' + JSON.stringify(this.state.id));
        CurveDataService.updateCurveData(curveData).then( res => {
            this.props.history.push('/curveDatas');
        });
    }


    cancel(){
        this.props.history.push('/curveDatas');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update CurveData</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                        </div>
                                        <button className="btn btn-success" onClick={this.updateCurveData}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdateCurveDataComponent
